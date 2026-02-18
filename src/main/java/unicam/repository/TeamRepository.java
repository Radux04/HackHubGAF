package unicam.repository;


import unicam.model.team.Team;

public interface TeamRepository {

    boolean existsByNome(String nome);
    Team save(Team team);
    Team findTeamByCoordinatoreId(int coordinatoreId);
    Team getTeamById(int id);
}
