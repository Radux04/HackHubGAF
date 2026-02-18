package unicam.repository;

import unicam.model.consegna.Consegna;

import java.util.HashMap;
import java.util.Map;

public class InMemoryConsegnaRepo implements ConsegnaRepository {

    private Map<Long, Consegna> consegne;

    public InMemoryConsegnaRepo() {
        consegne = new HashMap<Long, Consegna>();
    }

    @Override
    public Consegna save(Consegna consegna) {
        return consegne.put(consegna.getId(), consegna);
    }


    @Override
    public boolean contains(Consegna consegna) {
        for (Consegna c : consegne.values()) {
            if (c.getSottomissione() == consegna.getSottomissione() && c.getIdIscrizione() == consegna.getIdIscrizione()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(Long idConsegna) {
        consegne.remove(idConsegna);
    }
}
