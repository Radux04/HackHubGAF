package unicam.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unicam.dto.iscrizione.IscrizioneDTO;
import unicam.model.iscrizione.Iscrizione;
import unicam.service.IscrizioneTeamService;


@RestController
@RequestMapping("/team")
public class IscrizioneTeamController {
    private final IscrizioneTeamService iscrizioneTeamService;

    public IscrizioneTeamController(IscrizioneTeamService iscrizioneTeamService) {
        this.iscrizioneTeamService = iscrizioneTeamService;
    }

    @PostMapping
    public Iscrizione iscriviTeam(@RequestBody IscrizioneDTO  iscrizioneDTO) {

        iscrizioneDTO.setTeamId(iscrizioneTeamService.controlloTeam(iscrizioneDTO.getCoordinatoreId()));

        return iscrizioneTeamService.iscriviTeam(iscrizioneDTO);
    }

}
