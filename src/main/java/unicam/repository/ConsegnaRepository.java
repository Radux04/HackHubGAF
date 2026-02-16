package unicam.repository;

import unicam.model.consegna.Consegna;

public interface ConsegnaRepository {
    Consegna save(Consegna consegna);
    boolean contains(Consegna consegna);
    void remove(int idConsegna);
}
