package unicam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import unicam.model.team.Team;

public interface TeamRepository extends JpaRepository<Team,Long> {
    boolean existsByNome(String nome);
    Team findTeamByCoordinatoreId(Long coordinatoreId);
}
