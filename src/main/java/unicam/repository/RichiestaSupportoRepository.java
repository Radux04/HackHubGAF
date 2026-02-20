package unicam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicam.model.Hackathon;
import unicam.model.RichiestaSupporto;

import java.util.List;
@Repository
public interface RichiestaSupportoRepository extends JpaRepository <RichiestaSupporto, Long> {
    List<RichiestaSupporto> findByHackathon(Hackathon hackathon);
}