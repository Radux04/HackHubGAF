package unicam.controller;

import org.springframework.web.bind.annotation.RequestBody;
import unicam.dto.invito.InvitoDTO;
import unicam.model.inviti.Invito;
import unicam.model.team.Team;
import unicam.service.TeamService;
import unicam.model.utenti.user.User;

public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    public Team creaTeam(String nome, String descrizione, Long idCoordinatore) {
        return teamService.creaTeam(nome, descrizione, idCoordinatore);
    }

    public Invito invita(@RequestBody InvitoDTO invitoDTO) {
        return teamService.invita(invitoDTO);
    }


    public boolean removeMemberById(Long idUser, Long idTeamMittente)
    { return teamService.removeMemberById(idUser, idTeamMittente); }
}