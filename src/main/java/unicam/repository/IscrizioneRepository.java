package unicam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.model.hackathon.entity.Hackathon;
import unicam.model.iscrizione.Iscrizione;
import unicam.model.team.Team;

public interface IscrizioneRepository extends JpaRepository<Iscrizione, Long> {

    int getHackatonByTeam(int idTeam);
}
