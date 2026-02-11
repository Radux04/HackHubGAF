package unicam.repository;

import unicam.model.iscrizione.Iscrizione;
import unicam.model.team.Team;

public interface IscrizioneRepository {
    Iscrizione save(Iscrizione iscrizione);
    int getHackatonByTeam(Team team);
}
