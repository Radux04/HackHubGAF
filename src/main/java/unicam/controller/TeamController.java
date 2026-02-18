package unicam.controller;

import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/creaTeam")
    public Team creaTeam(@RequestBody CreaTeamDTO creaTeamDTO) {
        return teamService.creaTeam(creaTeamDTO);
    }

    @PostMapping("/invita")
    public Invito invita(@RequestBody InvitoDTO invitoDTO) {
        return teamService.invita(invitoDTO);
    }

    @DeleteMapping("/{memberId}")
    public boolean removeMemberById(@PathVariable Long membroId){
        return teamService.removeMemberById(membroId);
    }
}