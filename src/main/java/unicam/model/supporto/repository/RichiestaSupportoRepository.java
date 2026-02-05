package unicam.model.supporto.repository;

import unicam.model.supporto.RichiestaSupporto;

import java.util.List;

public interface RichiestaSupportoRepository {
    RichiestaSupporto save(RichiestaSupporto richiestaSupporto);
    List<RichiestaSupporto> findByHackathonId(int hackathonId);
}