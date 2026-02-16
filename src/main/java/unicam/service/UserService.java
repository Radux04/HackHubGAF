package unicam.service;

import unicam.model.team.Team;
import unicam.repository.*;
import unicam.model.utenti.user.Ruoli;
import unicam.model.utenti.user.User;

public class UserService {
    private final TeamService teamService;
    private final TeamRepository inMemoryTeamRepository;
    private final UserRepository inMemoryUserRepository;
    private final InvitiRepository inMemoryInvitiRepository;

    public UserService(TeamService teamService, InMemoryTeamRepository inMemoryTeamRepository, InMemoryUserRepository inMemoryUserRepository, InMemoryInvitiRepo inMemoryInvitiRepository) {
        this.teamService = teamService;
        this.inMemoryTeamRepository = inMemoryTeamRepository;
        this.inMemoryUserRepository = inMemoryUserRepository;
        this.inMemoryInvitiRepository =  inMemoryInvitiRepository;
    }

    public boolean risponde(boolean risposta, int idInvito, int idUser) {
        User user = inMemoryUserRepository.findById(idUser);

        //User's role
        Ruoli r = user.getRuolo();
        //Team that sent the request
        Team mittente = inMemoryTeamRepository.getTeamById(inMemoryInvitiRepository.findById(idInvito).getTeamId());
        //User's role
        Team userTeam = inMemoryTeamRepository.getTeamById(user.getIdTeam());

        //user is a simple user
        if(r == Ruoli.UTENTE){
            if(risposta){
                return diventaMembro(mittente.getId(), idUser);
            }
        }
        //user is already a member of a team
        else if(r == Ruoli.MEMBROTEAM){
            if(risposta){
                return teamService.cambiaTeam(idUser, userTeam.getId(),  mittente.getId());
            }
        }
        //user is a coordinatore of a team
        else if (r == Ruoli.COORDINATORE) {
            if(risposta){
                teamService.cambiaCoordinatore(userTeam.getId(), userTeam.getMembri().get(1));
                return teamService.cambiaTeam(idUser, userTeam.getId(),  mittente.getId());
            }
        }
        return false;
    }

    public boolean diventaMembro(int idTeam, int idUser) {
        inMemoryUserRepository.findById(idUser).setIdTeam(idTeam);
        return inMemoryTeamRepository.getTeamById(idTeam).getMembri().add(idUser);
    }
}
