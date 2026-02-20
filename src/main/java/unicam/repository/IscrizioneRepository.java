package unicam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicam.model.Iscrizione;
import java.util.Optional;

@Repository
public interface IscrizioneRepository extends JpaRepository<Iscrizione, Long> {
    Optional<Iscrizione> findByTeamId(Long teamId);

    void removeIscrizioneByTeamId(Long teamId);
}
