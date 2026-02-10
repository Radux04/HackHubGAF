package unicam.model.team.repository;

import unicam.model.team.Team;
import unicam.model.utenti.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryTeamRepository implements TeamRepository {
    private final Map<Integer, Team> teamById = new HashMap<>();

    @Override
    public boolean existsByNome(String nome) {
        for(Team t : teamById.values()) {
            if(t.getNome().equals(nome))
                return true;
        }
        return false;
    }

    @Override
    public Team save(Team team) {
        if (team.getId() == 0) {
            team.setId(teamById.size());
        }
        teamById.put(team.getId(), team);
        return team;
    }

    @Override
    public Team findTeamByCoordinatoreId(int coordinatoreId) {
        for(Team t : teamById.values()) {
            if(t.getCoordinatore().getId() == coordinatoreId)
                return t;
        }
        return null;
    }

    @Override
    public Team getTeamById(int id) {
        return teamById.get(id);
    }
}