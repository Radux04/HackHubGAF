package unicam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicam.model.supporto.RichiestaSupporto;

import java.util.List;
@Repository
public interface RichiestaSupportoRepository extends JpaRepository <RichiestaSupporto, Long> {
    List<RichiestaSupporto> findByHackathonId(Long hackathonId);
}