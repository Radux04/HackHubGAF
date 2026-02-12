package unicam.repository;

import unicam.model.consegna.Consegna;
import unicam.model.hackathon.entity.Sottomissione;

public interface ConsegnaRepository {
    Consegna save(Consegna consegna);
    boolean contains(Consegna consegna);
}
