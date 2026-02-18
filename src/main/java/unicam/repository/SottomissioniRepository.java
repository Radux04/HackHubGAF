package unicam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicam.model.hackathon.entity.Sottomissione;
@Repository
public interface SottomissioniRepository extends JpaRepository<Sottomissione, Long> {

}
