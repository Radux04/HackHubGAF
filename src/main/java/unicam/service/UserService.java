package unicam.service;

import org.springframework.stereotype.Service;
import unicam.dto.team.CambiaCoordinatoreDTO;
import unicam.dto.team.CambiaTeamDTO;
import unicam.dto.DiventaMembroDTO;
import unicam.dto.RispostaDTO;
import unicam.model.Invito;
import unicam.model.Team;
import unicam.repository.*;
import unicam.model.Ruoli;
import unicam.model.User;

@Service
public class UserService {
    private final TeamService teamService;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final InvitiRepository invitiRepository;

    public UserService(TeamService teamService, TeamRepository teamRepository, UserRepository userRepository, InvitiRepository invitiRepository) {
        this.teamService = teamService;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.invitiRepository =  invitiRepository;
    }

    public boolean risponde(RispostaDTO rispostaDTO) {
        User user = userRepository.findById(rispostaDTO.idUser()).get();

        Invito invito = invitiRepository.findById(rispostaDTO.idInvito()).get();
        //User's role
        Ruoli r = user.getRuolo();
        //Team that sent the request
        Team mittente = teamRepository.findById(invito.getTeam().getId()).get();
        //User's role
        Team userTeam = teamRepository.findById(user.getTeam().getId()).get();

        //user is a simple user
        if(r == Ruoli.UTENTE){
            if(rispostaDTO.risposta()){
                DiventaMembroDTO diventaMembroDTO = new DiventaMembroDTO(invito.getTeam().getId(), invito.getDestinatario().getId());
                return diventaMembro(diventaMembroDTO);
            }
        }
        //user is already a member of a team
        else if(r == Ruoli.MEMBROTEAM){
            if(rispostaDTO.risposta()){
                CambiaTeamDTO cambiaTeamDTO = new CambiaTeamDTO(userTeam.getId(), invito.getTeam().getId(), user.getId());
                return teamService.cambiaTeam(cambiaTeamDTO);
            }
        }
        //user is a coordinatore of a team
        else if (r == Ruoli.COORDINATORE) {
            if(rispostaDTO.risposta()){
                CambiaTeamDTO cambiaTeamDTO = new CambiaTeamDTO(userTeam.getId(), invito.getTeam().getId(), user.getId());
                CambiaCoordinatoreDTO cambiaCoordinatoreDTO = new CambiaCoordinatoreDTO(mittente.getId(), user.getId());
                teamService.cambiaCoordinatore(cambiaCoordinatoreDTO);
                return teamService.cambiaTeam(cambiaTeamDTO);
            }
        }
        return false;
    }

    public boolean diventaMembro(DiventaMembroDTO diventaMembroDTO) {
        userRepository.findById(diventaMembroDTO.idUser()).get().setTeam(teamRepository.findById(diventaMembroDTO.idTeam()).get());
        return teamRepository.findById(diventaMembroDTO.idTeam()).get().getMembri().add(userRepository.findById(diventaMembroDTO.idUser()).get());
    }
}
