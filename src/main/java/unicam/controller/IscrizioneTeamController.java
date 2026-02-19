package unicam.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unicam.dto.iscrizione.IscrizioneDTO;
import unicam.model.iscrizione.Iscrizione;
import unicam.service.IscrizioneTeamService;


@RestController
@RequestMapping("/iscrizioneTeam")
@RequiredArgsConstructor
public class IscrizioneTeamController {
    private final IscrizioneTeamService iscrizioneTeamService;

    @PostMapping
    public Iscrizione iscriviTeam(@RequestBody IscrizioneDTO  iscrizioneDTO) {
        //se il team non partecipa già a un hackathon lo aggiungo al DTO
        iscrizioneDTO.setTeamId(iscrizioneTeamService.controlloTeam(iscrizioneDTO.getCoordinatoreId()));

        return iscrizioneTeamService.iscriviTeam(iscrizioneDTO);
    }

    public void annullaIscrizione(Long coordinatoreId){
        iscrizioneTeamService.annullaIscrizione(coordinatoreId);
    }
}
