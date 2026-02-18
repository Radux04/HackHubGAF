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

    public Team creaTeam(String nome, String descrizione, int idCoordinatore) {
        return teamService.creaTeam(nome, descrizione, idCoordinatore);
    }

    public Invito invita(int idUser, int idTeamMittente) {
        return teamService.invita(idUser, idTeamMittente);
    }


    public boolean removeMemberById(int idUser, int idTeamMittente)
    { return teamService.removeMemberById(idUser, idTeamMittente); }
}