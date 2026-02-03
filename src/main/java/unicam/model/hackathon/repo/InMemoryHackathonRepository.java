package unicam.model.hackathon.repo;

import unicam.model.hackathon.entity.Hackathon;

import java.util.HashMap;
import java.util.Map;

public class InMemoryHackathonRepository {
    private final Map<Integer, Hackathon> hackathonByID = new HashMap<>();

    public Hackathon save(Hackathon hackathon) {
        hackathonByID.put(hackathon.getId(), hackathon);
        return hackathon;
    }
}
