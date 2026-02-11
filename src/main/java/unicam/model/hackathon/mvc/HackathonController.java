package unicam.model.hackathon.mvc;

import unicam.model.hackathon.entity.DescrizioneHT;
import unicam.model.hackathon.entity.Hackathon;
import unicam.model.hackathon.entity.PlacementHT;
import unicam.model.hackathon.entity.StaffHT;
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

    public boolean richiediSupporto(Team team, User utente, String descrizione) {
        return hackathonService.richiediSupporto(team, utente, descrizione);
    }

    public List<RichiestaSupporto> visualizzaRichiestaSupporto(Hackathon hackaton) {
        return hackathonService.visualizzaRichiesteSupporto(hackaton);
    }

    public void creaSottomissione(File file, String descrizione, String titolo, Hackathon hackaton) {
        hackathonService.creaSottomissione(file, descrizione, titolo, hackaton);
    }
}
