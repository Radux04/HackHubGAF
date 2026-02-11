package unicam.controller;

import unicam.model.inviti.Invito;
import unicam.model.team.Team;
import unicam.service.TeamService;
import unicam.model.utenti.user.User;

public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    public Team creaTeam(String nome, String descrizione, User coordinatore) {
        return teamService.creaTeam(nome, descrizione, coordinatore);
    }

    public Invito invita(User user, Team mittente) {
        return teamService.invita(user, mittente);
    }
}