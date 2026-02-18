package unicam.controller;

import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unicam.dto.team.CreaTeamDTO;
import unicam.dto.team.InvitoDTO;
import unicam.dto.team.RemoveMemberDTO;
import unicam.model.inviti.Invito;
import unicam.model.team.Team;
import unicam.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    public Team creaTeam(@RequestBody CreaTeamDTO creaTeamDTO) {
        return teamService.creaTeam(creaTeamDTO);
    }

    public Invito invita(@RequestBody InvitoDTO invitoDTO) {
        return teamService.invita(invitoDTO);
    }


    public boolean removeMemberById(@RequestBody RemoveMemberDTO removeMemberDTO)
    { return teamService.removeMemberById(removeMemberDTO); }
}