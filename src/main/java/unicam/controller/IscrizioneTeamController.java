package unicam.controller;


import unicam.model.iscrizione.Iscrizione;
import unicam.service.IscrizioneTeamService;
import java.util.List;

public class IscrizioneTeamController {
    private final IscrizioneTeamService iscrizioneTeamService;

    public IscrizioneTeamController(IscrizioneTeamService iscrizioneTeamService) {
        this.iscrizioneTeamService = iscrizioneTeamService;
    }

    
    public Iscrizione iscriviTeam(int coordinatoreId, int idHackaton) {
        return iscrizioneTeamService.iscriviTeam(selezionePartecipanti(this.caricaMembri(iscrizioneTeamService.controlloTeam(coordinatoreId))), idHackaton, iscrizioneTeamService.controlloTeam(coordinatoreId));
    }

    private List<Integer> caricaMembri(int idTeam){
        return iscrizioneTeamService.caricaMembriTeam(idTeam);
    }

    private List<Integer> selezionePartecipanti(List<Integer> l){
        return iscrizioneTeamService.selezionePartecipanti(l);
    }
}
