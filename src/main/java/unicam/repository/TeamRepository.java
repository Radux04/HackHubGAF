package unicam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicam.model.team.Team;
@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
    boolean existsByNome(String nome);
    Team findTeamByCoordinatoreId(Long coordinatoreId);
}
