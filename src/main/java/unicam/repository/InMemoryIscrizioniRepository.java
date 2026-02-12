package unicam.repository;

import unicam.model.iscrizione.Iscrizione;
import unicam.model.team.Team;

import java.util.HashMap;
import java.util.Map;

public class InMemoryIscrizioniRepository implements IscrizioneRepository {
    private final Map<Integer, Iscrizione> iscrizioniById = new HashMap<>();

    @Override
    public Iscrizione save(Iscrizione iscrizione) {
        iscrizioniById.put(iscrizione.getId(), iscrizione);
        return iscrizione;
    }

    @Override
    public int getHackatonByTeam(int idTeam) {
        for(Iscrizione iscrizione : iscrizioniById.values()) {
            if(iscrizione.getTeamId() == idTeam) {
                return iscrizione.getHtId();
            }
        }
        return -1;
    }
}
