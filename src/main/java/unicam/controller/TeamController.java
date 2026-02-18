package unicam.controller;

import org.springframework.web.bind.annotation.RequestBody;
import unicam.dto.team.InvitoDTO;
import unicam.dto.team.RemoveMemberDTO;
import unicam.model.inviti.Invito;
import unicam.model.team.Team;
import unicam.service.TeamService;

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


    public boolean removeMemberById(@RequestBody RemoveMemberDTO removeMemberDTO)
    { return teamService.removeMemberById(removeMemberDTO); }
}