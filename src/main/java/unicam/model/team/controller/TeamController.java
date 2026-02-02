package unicam.model.team.controller;

import unicam.model.team.Team;
import unicam.model.team.service.TeamService;
import unicam.model.utenti.user.User;
import unicam.model.utenti.user.repository.UserRepository;

public class TeamController {
    private final TeamService teamService;
    private final UserRepository userRepository;

    public TeamController(TeamService teamService, UserRepository userRepository) {
        this.teamService = teamService;
        this.userRepository = userRepository;
    }

    public Team creaTeam(String nome, String descrizione, int utenteId) {
        User coordinatore = userRepository.findById(utenteId);
        if (coordinatore == null) {
            throw new IllegalArgumentException("Utente non trovato: " + utenteId);
        }
        return teamService.creaTeam(nome, descrizione, coordinatore);
    }
}