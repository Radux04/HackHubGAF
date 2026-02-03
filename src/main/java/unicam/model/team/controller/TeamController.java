package unicam.model.team.controller;

import unicam.model.team.Team;
import unicam.model.team.service.TeamService;
import unicam.model.utenti.user.User;
import unicam.model.utenti.user.repository.UserRepository;

public class TeamController {
    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    public Team creaTeam(String nome, String descrizione, User coordinatore) {
//        User coordinatore = userRepository.findById(utenteId);
//        if (coordinatore == null) {
//            throw new IllegalArgumentException("Utente non trovato: " + utenteId);
//        }
        return teamService.creaTeam(nome, descrizione, coordinatore);
    }
}