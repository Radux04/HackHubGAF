package unicam.model.utenti.mvc;

import unicam.model.inviti.Invito;
import unicam.model.team.Team;
import unicam.model.team.repository.InMemoryTeamRepository;
import unicam.model.team.mvc.TeamService;
import unicam.model.utenti.user.Ruoli;
import unicam.model.utenti.user.User;

public class UserService {

    private TeamService teamService;
    private InMemoryTeamRepository inMemoryTeamRepository;

    public  TeamService getTeamService() {
        return teamService;
    }

    public boolean risponde(boolean risposta, Invito invito, User user) {
        //User's role
        Ruoli r = user.getRuolo();
        //Team that sent the request
        Team mittente = inMemoryTeamRepository.getTeamById(invito.getTeamId());
        //User's role
        Team UserTeam = user.getTeam();
        //user is a simple user
        if(r == Ruoli.UTENTE){
            if(risposta){
                aggiungiMembro(mittente, user);
                return true;
            }
        }
        else if(r == Ruoli.MEMBROTEAM){
            if(risposta){
                teamService.cambiaTeam();
            }
        }
    }

    public boolean nuovoCoordinatore(User membroTeam) {
        return true;
    }

    public boolean aggiungiMembro(Team team, User user) {
        return true;
    }
}
