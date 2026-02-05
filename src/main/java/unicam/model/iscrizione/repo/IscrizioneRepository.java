package unicam.model.iscrizione.repo;

import unicam.model.iscrizione.Iscrizione;
import unicam.model.team.Team;

public interface IscrizioneRepository {
    Iscrizione save(Iscrizione iscrizione);
    int getHackatonByTeam(Team team);
}
