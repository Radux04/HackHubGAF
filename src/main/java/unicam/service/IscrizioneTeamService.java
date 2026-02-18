package unicam.service;

import org.springframework.stereotype.Service;
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

@Service
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
            Team t = teamRepository.findByCoordinatore(u.get());
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

        Hackathon hackathon = hackathonRepository.findById(iscrizioneDTO.getIdHackathon()).get();

        User user = userRepository.findById(iscrizioneDTO.getCoordinatoreId()).get();

        Team team = teamRepository.findByCoordinatore(user);

        if(hackathon.getStato() != StatiHackathon.IN_ISCRIZIONE){
            throw new IllegalArgumentException();
        }

        if(hackathon.getMaxSize() < team.getMembri().size()){
            throw new IllegalArgumentException("Il tuo team ha troppi membri per partecipare");
        }

        if(user.getRuolo()!= Ruoli.COORDINATORE) { throw new IllegalArgumentException("Non puoi fare questa azione, non sei Coordinatore"); }

        teamRepository.findByCoordinatore(user).setOccupato(true);
        for(User u : team.getMembri()){
            if(userRepository.findById(u.getId()).isPresent()){
                u.setOccupato(true);
            }
        }

        IscrizioneBuilder ib = new IscrizioneBuilder();
        ib.buildHackatho(hackathon).buildTeam(team);
        Iscrizione iscrizione = ib.build();

        iscrizioneRepository.save(iscrizione);
        return iscrizione;
    }

}
