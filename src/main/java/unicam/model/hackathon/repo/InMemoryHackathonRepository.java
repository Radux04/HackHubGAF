package unicam.model.hackathon.repo;

import unicam.model.hackathon.entity.Hackathon;
import unicam.model.team.Team;

import java.util.HashMap;
import java.util.Map;

public class InMemoryHackathonRepository implements  HackathonRepository {
    private final Map<Integer, Hackathon> hackathonByID = new HashMap<>();

    @Override
    public Hackathon save(Hackathon hackathon) {
        hackathonByID.put(hackathon.getId(), hackathon);
        return hackathon;
    }
}
