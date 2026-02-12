package unicam.repository;

import unicam.model.hackathon.entity.Hackathon;

import java.util.List;

public interface HackathonRepository {

    Hackathon save(Hackathon hackathon);
    Hackathon getHackathonById(int idHackaton);
}
