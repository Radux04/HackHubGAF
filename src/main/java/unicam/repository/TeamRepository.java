package unicam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicam.model.team.Team;
import unicam.model.utenti.user.User;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
    boolean findByNome(String nome);
    Team findByCoordinatore(User coordinatore);
}
