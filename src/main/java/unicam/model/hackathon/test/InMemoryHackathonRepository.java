package unicam.model.hackathon.test;

import unicam.model.hackathon.entity.Hackathon;
import unicam.model.utenti.staff.Staff;

import java.util.HashMap;
import java.util.Map;

public class InMemoryHackathonRepository {
    private final Map<Integer, Hackathon> hackathonByID = new HashMap<>();

    public void save(Hackathon hackathon) {
        hackathonByID.put(hackathon.getId(), hackathon);
    }
}
