package unicam.model.supporto.repository;

import unicam.model.hackathon.mvc.HackathonService;
import unicam.model.supporto.RichiestaSupporto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRichiestaSupportoRepository implements RichiestaSupportoRepository {
    //private final Map<Integer, RichiestaSupporto> byId = new HashMap<>();
    private final Map<Integer, List<RichiestaSupporto>> byHackathon = new HashMap<>();
    //private int nextId = 1;

    @Override
    public RichiestaSupporto save(RichiestaSupporto richiesta) {
        if(byHackathon.get(richiesta.getHackathonId()) != null) {
            List<RichiestaSupporto> list = byHackathon.get(richiesta.getHackathonId());
            list.add(richiesta);
            byHackathon.replace(richiesta.getHackathonId(), list);
        }
        else{
            // se non c'è una lista per questo hackathon creala e mettila nella mappa e restituiscila
            List<RichiestaSupporto> lista = new ArrayList<>();
            lista.add(richiesta);
            byHackathon.put(richiesta.getHackathonId(), lista);
        }

        return richiesta;
    }

    @Override
    public List<RichiestaSupporto> findByHackathonId(int hackathonId) {
        return new ArrayList<>(byHackathon.getOrDefault(hackathonId, new ArrayList<>()));
    }
}