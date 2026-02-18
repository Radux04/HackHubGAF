package unicam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.model.consegna.Consegna;

public interface ConsegnaRepository extends JpaRepository<Consegna, Long> {
    Consegna save(Consegna consegna);
    boolean contains(Consegna consegna);
    void remove(Long idConsegna);
}
