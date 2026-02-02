package unicam.model.hackathon.mvc;

import unicam.model.hackathon.entity.DescrizioneHT;
import unicam.model.hackathon.entity.Hackathon;
import unicam.model.hackathon.entity.PlacementHT;
import unicam.model.hackathon.entity.StaffHT;
import unicam.model.utenti.staff.Staff;

public class HackathonController {
    private HackathonService hackathonService;

    public HackathonController(HackathonService hackathonService) {
        this.hackathonService = hackathonService;
    }

    public Hackathon CreaHackathon(DescrizioneHT descrizione, PlacementHT placement, StaffHT staff, String nome, Staff organizzatore) {
        // Implementation goes here
        return hackathonService.CreaHackathon(descrizione, placement, staff, nome, organizzatore);
    }
}
