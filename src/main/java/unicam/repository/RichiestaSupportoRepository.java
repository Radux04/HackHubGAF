package unicam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.model.supporto.RichiestaSupporto;

import java.util.List;

public interface RichiestaSupportoRepository extends JpaRepository <RichiestaSupporto, Long> {
    List<RichiestaSupporto> findByHackathonId(Long hackathonId);
}