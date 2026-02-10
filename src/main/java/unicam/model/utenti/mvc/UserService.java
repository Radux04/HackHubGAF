package unicam.model.utenti.mvc;

import unicam.model.inviti.Invito;
import unicam.model.team.Team;
import unicam.model.team.repository.InMemoryTeamRepository;
import unicam.model.team.mvc.TeamService;
import unicam.model.utenti.user.Ruoli;
import unicam.model.utenti.user.User;

public class UserService {

    private final TeamService teamService;
    private final InMemoryTeamRepository inMemoryTeamRepository;

    public UserService(TeamService teamService, InMemoryTeamRepository inMemoryTeamRepository) {
        this.teamService = teamService;
        this.inMemoryTeamRepository = inMemoryTeamRepository;
    }

    public boolean risponde(boolean risposta, Invito invito, User user) {
        //User's role
        Ruoli r = user.getRuolo();
        //Team that sent the request
        Team mittente = inMemoryTeamRepository.getTeamById(invito.getTeamId());
        //User's role
        Team userTeam = user.getTeam();

        //user is a simple user
        if(r == Ruoli.UTENTE){
            if(risposta){
                return aggiungiMembro(mittente, user);
            }
        }
        //user is already a member of a team
        else if(r == Ruoli.MEMBROTEAM){
            if(risposta){
                return teamService.cambiaTeam(user, userTeam,  mittente);
            }
        }
        //user is a coordinatore of a team
        else if (r == Ruoli.COORDINATORE) {
            if(risposta){
                teamService.cambiaCoordinatore(userTeam, userTeam.getMembri().get(1));
                return teamService.cambiaTeam(user, userTeam,  mittente);
            }
        }
        return false;
    }

    public boolean aggiungiMembro(Team team, User user) {
        user.setTeam(team);
        return team.getMembri().add(user);
    }
}
