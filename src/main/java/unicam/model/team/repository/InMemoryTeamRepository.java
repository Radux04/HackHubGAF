package unicam.model.team.repository;

import unicam.model.team.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryTeamRepository implements TeamRepository {
    private final Map<Integer, Team> byId = new HashMap<>();
    private final Map<String, Integer> byNome = new HashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public boolean existsByNome(String nome) {
        return nome != null && byNome.containsKey(nome);
    }

    @Override
    public Team save(Team team) {
        if (team.getId() == 0) {
            team.setId(nextId.getAndIncrement());
        }
        byId.put(team.getId(), team);
        if (team.getNome() != null) {
            byNome.put(team.getNome(), team.getId());
        }
        return team;
    }
}