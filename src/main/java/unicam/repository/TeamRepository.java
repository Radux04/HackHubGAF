package unicam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicam.model.Team;
import unicam.model.User;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
    boolean existsByNome(String nome);
    Team findByCoordinatore(User coordinatore);
}
