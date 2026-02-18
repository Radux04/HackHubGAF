package unicam.service;

import unicam.dto.iscrizione.IscrizioneDTO;
import unicam.model.hackathon.entity.Hackathon;
import unicam.model.hackathon.entity.StatiHackathon;
import unicam.model.iscrizione.Iscrizione;
import unicam.model.iscrizione.builder.IscrizioneBuilder;
import unicam.model.utenti.user.Ruoli;
import unicam.model.utenti.user.User;
import unicam.repository.*;
import unicam.model.team.Team;

import java.util.Optional;

public class IscrizioneTeamService {

    private final TeamRepository teamRepository;
    private final IscrizioneRepository iscrizioneRepository;
    private final HackathonRepository hackathonRepository;
    private final UserRepository userRepository;

    public IscrizioneTeamService(TeamRepository teamRepository, IscrizioneRepository iscrizioneRepository, HackathonRepository hackathonRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.iscrizioneRepository = iscrizioneRepository;
        this.hackathonRepository = hackathonRepository;
        this.userRepository = userRepository;
    }

    //controlla se un team è impegnato in un hackaton restituendo l'id del team in questione
    public Long controlloTeam(Long coordinatoreId){
        Optional<User> u =  userRepository.findById(coordinatoreId);
        if(u.isPresent()){
            Team t = teamRepository.findByCoordinatore(u);
            if(t.isOccupato()){
                throw new IllegalArgumentException();
            }
            else {
                return t.getId();
            }
        }
        return null;
    }

    public Iscrizione iscriviTeam(IscrizioneDTO iscrizioneDTO){

        Optional<Hackathon> hackathon = hackathonRepository.findById(iscrizioneDTO.getIdHackathon());

        Team team = teamRepository.findByCoordinatoreId(iscrizioneDTO.getCoordinatoreId());

        Optional<User> user = userRepository.findById(iscrizioneDTO.getCoordinatoreId());

        if(hackathon.get().getStato() != StatiHackathon.IN_ISCRIZIONE){
            throw new IllegalArgumentException();
        }

        if(hackathon.get().getMaxSize() < team.getMembri().size()){
            throw new IllegalArgumentException("Il tuo team ha troppi membri per partecipare");
        }

        if(user.get().getRuolo()!= Ruoli.COORDINATORE) { throw new IllegalArgumentException("Non puoi fare questa azione, non sei Coordinatore"); }

        IscrizioneBuilder ib = new IscrizioneBuilder();
        ib.buildTeamId(idTeam);
        ib.buildHTId(idHackathon);
        team.setOccupato(true);
        for(int u : team.getMembri()){
            inMemoryUserRepository.findById(u).setOccupato(true);
        }
        inMemoryTeamRepository.save(team);
        inMemoryIscrizioniRepository.save(ib.build());
        return ib.build();
    }

}
