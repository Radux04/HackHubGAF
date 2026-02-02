package unicam.model.team.repository;


import unicam.model.team.Team;

public interface TeamRepository {

    boolean existsByNome(String nome);
    Team save(Team team);
}
