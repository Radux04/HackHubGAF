package unicam.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unicam.dto.IscrizioneDTO;
import unicam.model.Iscrizione;
import unicam.service.IscrizioneTeamService;


@RestController
@RequestMapping("/iscrizioneTeam")
@RequiredArgsConstructor
public class IscrizioneTeamController {
    private final IscrizioneTeamService iscrizioneTeamService;

    @PostMapping
    public Iscrizione iscriviTeam(@RequestBody IscrizioneDTO  iscrizioneDTO) {
        return iscrizioneTeamService.iscriviTeam(iscrizioneDTO);
    }

    @DeleteMapping
    public void annullaIscrizione(@PathVariable Long coordinatoreId){
        iscrizioneTeamService.annullaIscrizione(coordinatoreId);
    }
}
