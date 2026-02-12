package unicam.repository;

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

    @Override
    public Invito findById(long id) {
        return invites.get(id);
    }
}
