package unicam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unicam.dto.hackathon.*;
import unicam.model.hackathon.entity.Hackathon;
import unicam.service.HackathonService;
import unicam.model.supporto.RichiestaSupporto;

import java.util.List;

@RestController
@RequestMapping("/hackathon")
@RequiredArgsConstructor
public class HackathonController {
    private final HackathonService hackathonService;

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
    public void creaSottomissione(@RequestBody CreaSottomissioneDTO creaSottomissioneDTO) {
        hackathonService.creaSottomissione(creaSottomissioneDTO);
    }
}
