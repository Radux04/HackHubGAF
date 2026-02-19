package unicam.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unicam.dto.iscrizione.IscrizioneDTO;
import unicam.model.iscrizione.Iscrizione;
import unicam.service.IscrizioneTeamService;
import unicam.service.mediator.IscrizioneTeamMediator;


@RestController
@RequestMapping("/iscrizioneTeam")
@RequiredArgsConstructor
public class IscrizioneTeamController {
    // Invece di iniettare direttamente il service, iniettiamo il mediator.
//    private final IscrizioneTeamService iscrizioneTeamService;
//
//    @PostMapping
//    public Iscrizione iscriviTeam(@RequestBody IscrizioneDTO  iscrizioneDTO) {
//        //se il team non partecipa già a un hackathon lo aggiungo al DTO
//        iscrizioneDTO.setTeamId(iscrizioneTeamService.controlloTeam(iscrizioneDTO.getCoordinatoreId()));
//
//        return iscrizioneTeamService.iscriviTeam(iscrizioneDTO);
//    }

    private final IscrizioneTeamMediator iscrizioneTeamMediator;

    @PostMapping
    public Iscrizione iscriviTeam(@RequestBody IscrizioneDTO iscrizioneDTO) {

        // 1) uso il mediator per verificare se il team è libero
        Long teamId = iscrizioneTeamMediator.verificaDisponibilitaTeam(iscrizioneDTO.getCoordinatoreId());
        iscrizioneDTO.setTeamId(teamId);

        // 2) poi chiamo il mediator per fare tutta l'iscrizione
        return iscrizioneTeamMediator.iscriviTeam(iscrizioneDTO);
    }
}
