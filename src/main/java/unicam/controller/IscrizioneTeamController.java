package unicam.controller;

import unicam.model.hackathon.entity.Hackathon;
import unicam.model.iscrizione.Iscrizione;
import unicam.service.IscrizioneTeamService;
import unicam.model.team.Team;
import unicam.model.utenti.user.User;

import java.util.ArrayList;
import java.util.List;

public class IscrizioneTeamController {
    private final IscrizioneTeamService iscrizioneTeamService;

    public IscrizioneTeamController(IscrizioneTeamService iscrizioneTeamService) {
        this.iscrizioneTeamService = iscrizioneTeamService;
    }

    
    public Iscrizione iscriviTeam(int coordinatoreId, Hackathon h){
        Team t = iscrizioneTeamService.controlloTeam(coordinatoreId);
        List<User> membri = this.caricaMembri(t);

        membri = selezionePartecipanti(membri);

        return iscrizioneTeamService.iscriviTeam(membri, h, t);
    }

    private List<User> caricaMembri(Team team){
        return iscrizioneTeamService.caricaMembriTeam(team);
    }

    private List<User> selezionePartecipanti(List<User> l){
        return iscrizioneTeamService.selezionePartecipanti(l);
    }
}
