package unicam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicam.model.hackathon.entity.Segnalazione;

@Repository
public interface SegnalazioneRepository extends JpaRepository<Segnalazione, Long> {
}
