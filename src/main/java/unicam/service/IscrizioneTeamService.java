package unicam.service;

import org.springframework.stereotype.Service;
import unicam.dto.IscrizioneDTO;
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

    public Iscrizione iscriviTeam(IscrizioneDTO iscrizioneDTO){

        Hackathon hackathon = hackathonRepository.findById(iscrizioneDTO.idHackathon()).get();

        User user = userRepository.findById(iscrizioneDTO.coordinatoreId()).get();

        Team team = teamRepository.findByCoordinatore(user);

        if(team.isOccupato()){
            throw new IllegalArgumentException("Il tuo team è già iscritto a un hackathon");
        }

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

    public void annullaIscrizione(Long coordinatoreId){


        //ottengo il coordinatore tramite l'id
        Optional<User> coordinatore =  userRepository.findById(coordinatoreId);
        //ottengo il team tramite il coordinatore
        Team team = coordinatore.get().getTeam();

        //rimuovo l'iscrizione del team
        iscrizioneRepository.removeIscrizioneByTeamId(team.getId());

        //imposto occupato come liberi
        teamRepository.findById(team.getId()).get().setOccupato(false);

        //imposto lo stato di tutti i membri del team a occupato
        for(User u : teamRepository.findById(team.getId()).get().getMembri()){
            u.setOccupato(false);
        }
    }

}
