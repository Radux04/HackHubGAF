package unicam.controller;

import org.springframework.web.bind.annotation.*;
import unicam.dto.hackathon.DescrizioneHT;
import unicam.dto.hackathon.HackathonRequest;
import unicam.model.hackathon.entity.Hackathon;
import unicam.dto.hackathon.PlacementHT;
import unicam.dto.hackathon.StaffHT;
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
    public Hackathon CreaHackathon(@RequestBody HackathonRequest hackathonRequest) {
        return hackathonService.CreaHackathon(hackathonRequest);
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
