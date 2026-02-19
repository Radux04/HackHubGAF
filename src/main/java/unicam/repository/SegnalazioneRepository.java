package unicam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.model.hackathon.entity.Segnalazione;

public interface SegnalazioneRepository extends JpaRepository<Segnalazione, Long> {
}
