package unicam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.model.inviti.Invito;

public interface InvitiRepository extends JpaRepository<Invito, Long> {

}
