package unicam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.model.Hackathon;

public interface HackathonRepository extends JpaRepository<Hackathon, Long> {


    Long id(Long id);
}
