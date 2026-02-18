package unicam.controller;

import org.springframework.web.bind.annotation.*;
import unicam.model.hackathon.entity.DescrizioneHT;
import unicam.model.hackathon.entity.Hackathon;
import unicam.model.hackathon.entity.PlacementHT;
import unicam.model.hackathon.entity.StaffHT;
import unicam.service.HackathonService;
import unicam.model.supporto.RichiestaSupporto;

import java.util.List;

@RestController
@RequestMapping("/hackathon")
public class HackathonController {
    private final HackathonService hackathonService;

    public HackathonController(HackathonService hackathonService) {
        this.hackathonService = hackathonService;
    }

    @PostMapping
    public Hackathon CreaHackathon(DescrizioneHT descrizione, PlacementHT placement, StaffHT staff, String nome, Long idOrganizzatore) {
        return hackathonService.CreaHackathon(descrizione, placement, staff, nome, idOrganizzatore);
    }

    @PostMapping("/richiesteSupporto")
    public RichiestaSupporto richiestaSupporto(@RequestBody Long idTeam,@RequestBody String descrizione) {
        return hackathonService.richiediSupporto(idTeam, descrizione);
    }

    @GetMapping
    public List<RichiestaSupporto> visualizzaRichiestaSupporto(@PathVariable Long idHackaton) {
        return hackathonService.visualizzaRichiesteSupporto(idHackaton);
    }

    @PostMapping("/sottomissione")
    public void creaSottomissione(@RequestBody String descrizione,@RequestBody String titolo,@RequestBody Long idHackaton) {
        hackathonService.creaSottomissione(descrizione, titolo, idHackaton);
    }
}
