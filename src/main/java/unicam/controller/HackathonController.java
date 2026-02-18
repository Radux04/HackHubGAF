package unicam.controller;

import org.springframework.web.bind.annotation.*;
import unicam.dto.hackathon.*;
import unicam.model.hackathon.entity.Hackathon;
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
    public RichiestaSupporto richiestaSupporto(@RequestBody RichiestaSupportoDTO richiestaSupportoDTO) {
        return hackathonService.richiediSupporto(richiestaSupportoDTO);
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
