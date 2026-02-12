package unicam.repository;

import unicam.model.consegna.Consegna;
import unicam.model.hackathon.entity.Sottomissione;

import java.util.HashMap;
import java.util.Map;

public class InMemoryConsegnaRepo implements ConsegnaRepository {

    private Map<Integer, Consegna> consegne;

    public InMemoryConsegnaRepo() {
        consegne = new HashMap<Integer, Consegna>();
    }

    @Override
    public Consegna save(Consegna consegna) {
        return consegne.put(consegna.getId(), consegna);
    }


    @Override
    public boolean contains(Consegna consegna) {
        for (Consegna c : consegne.values()) {
            if (c.getSottomissione().equals(consegna.getSottomissione()) && c.getIdIscrizione() == consegna.getIdIscrizione()) {
                return true;
            }
        }
        return false;
    }
}
