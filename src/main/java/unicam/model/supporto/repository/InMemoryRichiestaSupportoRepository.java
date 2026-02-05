package unicam.model.supporto.repository;

import unicam.model.supporto.RichiestaSupporto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRichiestaSupportoRepository implements RichiestaSupportoRepository {
    private final Map<Integer, RichiestaSupporto> byId = new HashMap<>();
    private final Map<Integer, List<RichiestaSupporto>> byHackathon = new HashMap<>();
    private int nextId = 1;

    @Override
    public RichiestaSupporto save(RichiestaSupporto richiesta) {
        // se la richiesta non ha ancora un id le assegna un nuovo id incrementale
        if (richiesta.getId() == 0) {
            richiesta.setId(nextId++);
        }
        byId.put(richiesta.getId(), richiesta);

        // se non c'è una lista per questo hackathon creala e mettila nella mappa e restituiscila
        List<RichiestaSupporto> lista = byHackathon.get(richiesta.getHackathonId());
        if (lista == null) {
            lista = new ArrayList<>();
            byHackathon.put(richiesta.getHackathonId(), lista);
        }
        lista.add(richiesta);

        return richiesta;
    }

    @Override
    public List<RichiestaSupporto> findByHackathonId(int hackathonId) {
        return new ArrayList<>(byHackathon.getOrDefault(hackathonId, new ArrayList<>()));
    }
}