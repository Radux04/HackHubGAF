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
//package unicam.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import unicam.dto.iscrizione.IscrizioneDTO;
//import unicam.model.hackathon.entity.Hackathon;
//import unicam.model.hackathon.entity.StatiHackathon;
//import unicam.model.iscrizione.Iscrizione;
//import unicam.model.iscrizione.builder.IscrizioneBuilder;
//import unicam.model.team.Team;
//import unicam.model.utenti.user.Ruoli;
//import unicam.model.utenti.user.User;
//import unicam.repository.HackathonRepository;
//import unicam.repository.IscrizioneRepository;
//import unicam.repository.TeamRepository;
//import unicam.repository.UserRepository;
//
//@Service
//@RequiredArgsConstructor
//public class IscrizioneTeamService { // <- questo è il "mediator" del caso d'uso
//
//    private final TeamRepository teamRepository;
//    private final IscrizioneRepository iscrizioneRepository;
//    private final HackathonRepository hackathonRepository;
//    private final UserRepository userRepository;
//
//    /**
//     * Mediator/UseCase: orchestration completa del caso d'uso "Iscrivi Team all'Hackathon".
//     */
//    @Transactional
//    public Iscrizione execute(IscrizioneDTO dto) {
//
//        // 1) Recupero coordinatore
//        User coordinatore = userRepository.findById(dto.getCoordinatoreId())
//                .orElseThrow(() -> new IllegalArgumentException("Coordinatore non trovato"));
//
//        if (coordinatore.getRuolo() != Ruoli.COORDINATORE) {
//            throw new IllegalArgumentException("Non puoi fare questa azione: non sei Coordinatore");
//        }
//
//        // 2) Recupero team dal coordinatore
//        Team team = teamRepository.findByCoordinatore(coordinatore);
//        if (team == null) {
//            throw new IllegalArgumentException("Team del coordinatore non trovato");
//        }
//
//        if (team.isOccupato()) {
//            throw new IllegalArgumentException("Il team risulta già iscritto a un hackathon non concluso");
//        }
//
//        // 3) Recupero hackathon
//        Hackathon hackathon = hackathonRepository.findById(dto.getIdHackathon())
//                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));
//
//        if (hackathon.getStato() != StatiHackathon.IN_ISCRIZIONE) {
//            throw new IllegalArgumentException("Hackathon non in stato IN_ISCRIZIONE");
//        }
//
//        if (hackathon.getMaxSize() < team.getMembri().size()) {
//            throw new IllegalArgumentException("Il team ha troppi membri per partecipare a questo hackathon");
//        }
//
//        // (opzionale ma consigliato) evita doppie iscrizioni stesso hackathon/team
//        // if (iscrizioneRepository.existsByHackathonIdAndTeamId(hackathon.getId(), team.getId())) {
//        //     throw new IllegalArgumentException("Il team risulta già iscritto a questo hackathon");
//        // }
//
//        // 4) Aggiorno stati "occupato"
//        team.setOccupato(true);
//
//        // Se User è una entity gestita e la relazione è corretta, basta settare e il flush avviene a fine TX.
//        // Se NON hai cascade/managed state, salva esplicitamente gli user.
//        for (User membro : team.getMembri()) {
//            membro.setOccupato(true);
//        }
//
//        // 5) Creo iscrizione (Builder come già usi)
//        Iscrizione iscrizione = new IscrizioneBuilder()
//                .buildHackatho(hackathon)
//                .buildTeam(team)
//                .build();
//
//        // 6) Persist
//        return iscrizioneRepository.save(iscrizione);
//    }
//}

