package unicam.repository;

import unicam.model.hackathon.entity.Hackathon;

import java.util.HashMap;
import java.util.Map;

public class InMemoryHackathonRepository implements  HackathonRepository {
    private final Map<Long, Hackathon> hackathonByID = new HashMap<>();

    @Override
    public Hackathon save(Hackathon hackathon) {
        hackathonByID.put(hackathon.getId(), hackathon);
        return hackathon;
    }

    @Override
    public Hackathon getHackathonById(Long id) {
        return hackathonByID.get(id);
    }
}
