package unicam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.model.hackathon.entity.Hackathon;

import java.util.List;

public interface HackathonRepository extends JpaRepository<Hackathon, Long> {
    
}
