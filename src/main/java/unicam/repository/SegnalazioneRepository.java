package unicam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicam.model.Segnalazione;

@Repository
public interface SegnalazioneRepository extends JpaRepository<Segnalazione, Long> {
}
