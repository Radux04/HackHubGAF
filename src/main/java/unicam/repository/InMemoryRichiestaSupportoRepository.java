package unicam.repository;

import unicam.model.supporto.RichiestaSupporto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRichiestaSupportoRepository implements RichiestaSupportoRepository {
    private final Map<Integer, List<RichiestaSupporto>> RichiesteSupportoByHackathonId = new HashMap<>();

    @Override
    public RichiestaSupporto save(RichiestaSupporto richiesta) {
        if(RichiesteSupportoByHackathonId.get(richiesta.getHackathonId()) != null) {
            List<RichiestaSupporto> list = RichiesteSupportoByHackathonId.get(richiesta.getHackathonId());
            list.add(richiesta);
            RichiesteSupportoByHackathonId.replace(richiesta.getHackathonId(), list);
        }
        else{
            // se non c'è una lista per questo hackathon creala e mettila nella mappa e restituiscila
            List<RichiestaSupporto> lista = new ArrayList<>();
            lista.add(richiesta);
            RichiesteSupportoByHackathonId.put(richiesta.getHackathonId(), lista);
        }

        return richiesta;
    }

    @Override
    public List<RichiestaSupporto> findByHackathonId(int hackathonId) {
        return RichiesteSupportoByHackathonId.get(hackathonId);
    }
}