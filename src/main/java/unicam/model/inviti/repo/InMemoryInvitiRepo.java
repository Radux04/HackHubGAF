package unicam.model.inviti.repo;

import unicam.model.inviti.Invito;

import java.util.HashMap;
import java.util.Map;

public class InMemoryInvitiRepo implements InvitiRepository {
    private Map<Integer, Invito> invites = new HashMap<>();

    @Override
    public Invito save(Invito invito) {
        invites.put(invito.getId(), invito);
        return invito;
    }
}
