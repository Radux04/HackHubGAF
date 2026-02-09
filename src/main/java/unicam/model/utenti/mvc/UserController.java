package unicam.model.utenti.mvc;

import unicam.model.inviti.Invito;
import unicam.model.utenti.user.User;
import unicam.model.utenti.user.repository.UserRepository;

public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public boolean risponde(boolean risposta, Invito invito, User user) {
        return userService.risponde(risposta, invito, user);
    }

    public boolean nuovoCoordinatore(User membroTeam) {
        return userService.nuovoCoordinatore(membroTeam);
    }

}
