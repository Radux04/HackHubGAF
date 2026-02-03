package unicam.model.iscrizione.repo;

import unicam.model.hackathon.entity.Hackathon;
import unicam.model.iscrizione.Iscrizione;

import java.util.HashMap;
import java.util.Map;

public class InMemoryIscrizioniRepository {
    private final Map<Integer, Iscrizione> iscrizioniById = new HashMap<>();

    public Iscrizione save(Iscrizione iscrizione) {
        iscrizioniById.put(iscrizione.getId(), iscrizione);
        return iscrizione;
    }
}
