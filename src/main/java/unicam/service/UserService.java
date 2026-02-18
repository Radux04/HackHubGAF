package unicam.service;

import unicam.dto.team.CambiaCoordinatoreDTO;
import unicam.dto.team.CambiaTeamDTO;
import unicam.dto.user.DiventaMembroDTO;
import unicam.dto.user.RispostaDTO;
import unicam.model.inviti.Invito;
import unicam.model.team.Team;
import unicam.repository.*;
import unicam.model.utenti.user.Ruoli;
import unicam.model.utenti.user.User;

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
        User user = userRepository.findById(rispostaDTO.getIdUser()).get();

        Invito invito = invitiRepository.findById(rispostaDTO.getIdInvito()).get();
        //User's role
        Ruoli r = user.getRuolo();
        //Team that sent the request
        Team mittente = teamRepository.findById(invito.getTeam().getId()).get();
        //User's role
        Team userTeam = teamRepository.findById(user.getTeam().getId()).get();

        //user is a simple user
        if(r == Ruoli.UTENTE){
            if(rispostaDTO.isRisposta()){
                DiventaMembroDTO diventaMembroDTO = new DiventaMembroDTO();
                diventaMembroDTO.setIdTeam(invito.getTeam().getId());
                diventaMembroDTO.setIdUser(invito.getDestinatario().getId());
                return diventaMembro(diventaMembroDTO);
            }
        }
        //user is already a member of a team
        else if(r == Ruoli.MEMBROTEAM){
            if(rispostaDTO.isRisposta()){
                CambiaTeamDTO cambiaTeamDTO = new CambiaTeamDTO();
                cambiaTeamDTO.setIdMembroTeam(user.getId());
                cambiaTeamDTO.setIdTeamAttuale(userTeam.getId());
                cambiaTeamDTO.setIdNuovoTeam(mittente.getId());
                return teamService.cambiaTeam(cambiaTeamDTO);
            }
        }
        //user is a coordinatore of a team
        else if (r == Ruoli.COORDINATORE) {
            if(rispostaDTO.isRisposta()){
                CambiaTeamDTO cambiaTeamDTO = new CambiaTeamDTO();
                cambiaTeamDTO.setIdMembroTeam(user.getId());
                cambiaTeamDTO.setIdTeamAttuale(userTeam.getId());
                cambiaTeamDTO.setIdNuovoTeam(mittente.getId());
                CambiaCoordinatoreDTO cambiaCoordinatoreDTO = new CambiaCoordinatoreDTO();
                cambiaCoordinatoreDTO.setIdNuovoCoordinatore(userTeam.getMembri().get(1).getId());
                cambiaCoordinatoreDTO.setIdNuovoCoordinatore(userTeam.getId());

                teamService.cambiaCoordinatore(cambiaCoordinatoreDTO);
                return teamService.cambiaTeam(cambiaTeamDTO);
            }
        }
        return false;
    }

    public boolean diventaMembro(DiventaMembroDTO diventaMembroDTO) {
        userRepository.findById(diventaMembroDTO.getIdUser()).get().setTeam(teamRepository.findById(diventaMembroDTO.getIdTeam()).get());
        return teamRepository.findById(diventaMembroDTO.getIdTeam()).get().getMembri().add(userRepository.findById(diventaMembroDTO.getIdUser()).get());
    }
}
