package unicam.controller;


import unicam.model.iscrizione.Iscrizione;
import unicam.service.IscrizioneTeamService;
import java.util.List;

public class IscrizioneTeamController {
    private final IscrizioneTeamService iscrizioneTeamService;

    public IscrizioneTeamController(IscrizioneTeamService iscrizioneTeamService) {
        this.iscrizioneTeamService = iscrizioneTeamService;
    }

    
    public Iscrizione iscriviTeam(Long coordinatoreId, Long idHackathon) {
        Long teamid = iscrizioneTeamService.controlloTeam(coordinatoreId);
        return iscrizioneTeamService.iscriviTeam(coordinatoreId, idHackathon, teamid);
    }

}
