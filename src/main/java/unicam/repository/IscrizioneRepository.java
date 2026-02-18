package unicam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicam.model.hackathon.entity.Hackathon;
import unicam.model.iscrizione.Iscrizione;
import unicam.model.team.Team;
@Repository
public interface IscrizioneRepository extends JpaRepository<Iscrizione, Long> {

    Long getHackatonByTeam(Long idTeam);
}
