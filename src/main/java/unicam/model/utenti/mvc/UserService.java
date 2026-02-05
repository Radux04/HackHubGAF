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
        Team userTeam = user.getTeam();
        //user is a simple user
        if(r == Ruoli.UTENTE){
            if(risposta){
                return aggiungiMembro(mittente, user);
            }
        }
        else if(r == Ruoli.MEMBROTEAM){
            if(risposta){
                return teamService.cambiaTeam(user, userTeam,  mittente);
            }
        }
    }

    public boolean nuovoCoordinatore(User membroTeam) {
        return true;
    }

    public boolean aggiungiMembro(Team team, User user) {
        user.setTeam(team);
        return team.getMembri().add(user);

    }
}
