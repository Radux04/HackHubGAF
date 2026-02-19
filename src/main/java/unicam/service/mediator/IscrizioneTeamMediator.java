package unicam.service.mediator;

import unicam.dto.iscrizione.IscrizioneDTO;
import unicam.model.iscrizione.Iscrizione;

public interface IscrizioneTeamMediator {
    /**
     * Controlla se il team (individuato dal coordinatore) è libero.
     * Ritorna l'id del team se è disponibile, altrimenti lancia eccezione.
     */
    Long verificaDisponibilitaTeam(Long coordinatoreId);

    /**
     * Esegue tutta l'iscrizione del team all'hackathon
     * (controlli su stato, dimensione team, ruolo, ecc.).
     */
    Iscrizione iscriviTeam(IscrizioneDTO dto);
}
