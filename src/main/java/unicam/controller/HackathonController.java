package unicam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unicam.dto.hackathon.*;
import unicam.model.Hackathon;
import unicam.service.HackathonService;
import unicam.model.RichiestaSupporto;

import java.util.List;

@RestController
@RequestMapping("/hackathon")
@RequiredArgsConstructor
public class HackathonController {
    private final HackathonService hackathonService;


    @PostMapping("/crea")
    public Hackathon CreaHackathon(@RequestBody HackathonRequest hackathonRequest) {
        return hackathonService.CreaHackathon(hackathonRequest);
    }

    @PostMapping("/richiesteSupporto")
    public RichiestaSupporto richiestaSupporto(@RequestBody RichiestaSupportoDTO richiestaSupportoDTO) {
        return hackathonService.richiediSupporto(richiestaSupportoDTO);
    }

    @GetMapping("/{idHackaton}/richiesteSupporto")
    public List<RichiestaSupporto> visualizzaRichiestaSupporto(@PathVariable Long idHackaton) {
        return hackathonService.visualizzaRichiesteSupporto(idHackaton);
    }

    @PostMapping("/creaSottomissione")
    public void creaSottomissione(@RequestBody CreaSottomissioneDTO creaSottomissioneDTO) {
        hackathonService.creaSottomissione(creaSottomissioneDTO);
    }


    @PostMapping("/segnalaTeam")
    public boolean segnalaTeam(SegnalaTeamDTO  segnalaTeamDTO) {
        hackathonService.segnalaTeam(segnalaTeamDTO);
        return true;
    }

    @PostMapping("/aggiungiMentore")
    public Hackathon aggiungiMentore(@RequestBody AggiungiMentoreDTO aggiungiMentoreDTO) {
        return hackathonService.aggiungiMentore(aggiungiMentoreDTO);
    }
}
