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


    public boolean segnalaTeam(Long teamId, Long hackathonId, Long mentoreId, String descrizione){
        hackathonService.segnalaTeam(teamId, hackathonId, mentoreId, descrizione);
        return true;
    }
}
