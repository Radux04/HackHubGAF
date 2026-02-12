package unicam.controller;

import unicam.model.inviti.Invito;
import unicam.service.UserService;
import unicam.model.utenti.user.User;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public boolean risponde(boolean risposta, int idInvito, int idUser) {
        return userService.risponde(risposta, idInvito, idUser);
    }
}
