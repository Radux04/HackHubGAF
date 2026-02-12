package unicam.repository;

import unicam.model.hackathon.entity.Hackathon;
import unicam.model.iscrizione.Iscrizione;
import unicam.model.team.Team;

public interface IscrizioneRepository {
    Iscrizione save(Iscrizione iscrizione);
    int getHackatonByTeam(int idTeam);
}
