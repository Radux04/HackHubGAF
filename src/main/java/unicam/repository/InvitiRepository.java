package unicam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicam.model.inviti.Invito;
@Repository
public interface InvitiRepository extends JpaRepository<Invito, Long> {

}
