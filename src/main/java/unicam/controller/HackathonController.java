package unicam.controller;

import unicam.model.hackathon.entity.DescrizioneHT;
import unicam.model.hackathon.entity.Hackathon;
import unicam.model.hackathon.entity.PlacementHT;
import unicam.model.hackathon.entity.StaffHT;
import unicam.service.HackathonService;
import unicam.model.supporto.RichiestaSupporto;
import unicam.model.team.Team;
import unicam.model.utenti.staff.Staff;
import unicam.model.utenti.user.User;

import java.io.File;
import java.util.List;

public class HackathonController {
    private final HackathonService hackathonService;

    public HackathonController(HackathonService hackathonService) {
        this.hackathonService = hackathonService;
    }
    
    public Hackathon CreaHackathon(DescrizioneHT descrizione, PlacementHT placement, StaffHT staff, String nome, Staff organizzatore) {
        return hackathonService.CreaHackathon(descrizione, placement, staff, nome, organizzatore);
    }

    public boolean richiestaSupporto(int idTeam, String descrizione) {
        return hackathonService.richiediSupporto(idTeam, descrizione);
    }

    public List<RichiestaSupporto> visualizzaRichiestaSupporto(int idHackaton) {
        return hackathonService.visualizzaRichiesteSupporto(idHackaton);
    }

    public void creaSottomissione(String descrizione, String titolo, int idHackaton) {
        hackathonService.creaSottomissione(descrizione, titolo, idHackaton);
    }
}
