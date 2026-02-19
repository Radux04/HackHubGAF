package unicam.service.mediator;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unicam.dto.iscrizione.IscrizioneDTO;
import unicam.model.hackathon.entity.Hackathon;
import unicam.model.hackathon.entity.StatiHackathon;
import unicam.model.iscrizione.Iscrizione;
import unicam.model.iscrizione.builder.IscrizioneBuilder;
import unicam.model.team.Team;
import unicam.model.utenti.user.Ruoli;
import unicam.model.utenti.user.User;
import unicam.repository.HackathonRepository;
import unicam.repository.IscrizioneRepository;
import unicam.repository.TeamRepository;
import unicam.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class IscrizioneTeamMediatorImpl implements IscrizioneTeamMediator{

    private final TeamRepository teamRepository;
    private final IscrizioneRepository iscrizioneRepository;
    private final HackathonRepository hackathonRepository;
    private final UserRepository userRepository;

    /**
     * Controlla se il team associato al coordinatore è libero.
     * Se occupato -> eccezione, se libero -> ritorna id team.
     */
    @Override
    public Long verificaDisponibilitaTeam(Long coordinatoreId) {

        User coordinatore = userRepository.findById(coordinatoreId)
                .orElseThrow(() -> new IllegalArgumentException("Coordinatore non trovato"));

        Team team = teamRepository.findByCoordinatore(coordinatore);
        if (team == null) {
            throw new IllegalArgumentException("Nessun team associato a questo coordinatore");
        }

        if (team.isOccupato()) {
            throw new IllegalArgumentException("Il team è già iscritto a un hackathon non concluso");
        }

        return team.getId();
    }

    /**
     * Mediator del caso d'uso: iscrivi team all'hackathon.
     */
    @Override
    @Transactional
    public Iscrizione iscriviTeam(IscrizioneDTO dto) {

        // 1) Recupero coordinatore
        User coordinatore = userRepository.findById(dto.getCoordinatoreId())
                .orElseThrow(() -> new IllegalArgumentException("Coordinatore non trovato"));

        if (coordinatore.getRuolo() != Ruoli.COORDINATORE) {
            throw new IllegalArgumentException("Non puoi fare questa azione: non sei Coordinatore");
        }

        // 2) Recupero team dal coordinatore
        Team team = teamRepository.findByCoordinatore(coordinatore);
        if (team == null) {
            throw new IllegalArgumentException("Team del coordinatore non trovato");
        }

        if (team.isOccupato()) {
            throw new IllegalArgumentException("Il team risulta già iscritto a un hackathon non concluso");
        }

        // 3) Recupero hackathon
        Hackathon hackathon = hackathonRepository.findById(dto.getIdHackathon())
                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));

        if (hackathon.getStato() != StatiHackathon.IN_ISCRIZIONE) {
            throw new IllegalArgumentException("Hackathon non in stato IN_ISCRIZIONE");
        }

        if (hackathon.getMaxSize() < team.getMembri().size()) {
            throw new IllegalArgumentException("Il team ha troppi membri per partecipare a questo hackathon");
        }

        // (opzionale ma forte) controllo doppia iscrizione team-hackathon
        // if (iscrizioneRepository.existsByHackathonIdAndTeamId(hackathon.getId(), team.getId())) {
        //     throw new IllegalArgumentException("Il team risulta già iscritto a questo hackathon");
        // }

        // 4) Aggiorno stati "occupato"
        team.setOccupato(true);
        team.getMembri().forEach(m -> m.setOccupato(true));

        // 5) Creo iscrizione con builder
        Iscrizione iscrizione = new IscrizioneBuilder()
                .buildHackatho(hackathon)
                .buildTeam(team)
                .build();

        return iscrizioneRepository.save(iscrizione);
    }

}
