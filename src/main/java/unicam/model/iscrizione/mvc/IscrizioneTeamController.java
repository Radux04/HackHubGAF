package unicam.model.iscrizione.mvc;

import unicam.model.hackathon.entity.Hackathon;
import unicam.model.iscrizione.Iscrizione;
import unicam.model.team.Team;
import unicam.model.utenti.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IscrizioneTeamController {
    private IscrizioneTeamService iscrizioneTeamService;

    public IscrizioneTeamController(IscrizioneTeamService iscrizioneTeamService) {
        this.iscrizioneTeamService = iscrizioneTeamService;
    }

    public Iscrizione iscriviTeam(int coordinatoreId, Hackathon h){
        Team t = iscrizioneTeamService.controlloTeam(coordinatoreId);
        List<User> membriId = this.caricaMembri(t);

        return selezionePartecipanti(membriId, h, t);
    }

    private List<User> caricaMembri(Team team){
        List<User> membriTeam = new ArrayList<>();
        for(User m : team.getMembri()){
            membriTeam.add(m);
        }
        return membriTeam;
    }

    private Iscrizione selezionePartecipanti(List<User> l, Hackathon h, Team t){
        return iscrizioneTeamService.iscriviTeam(l, h, t);
    }
}
