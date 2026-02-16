package unicam.controller;


import unicam.service.UserService;


public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public boolean risponde(boolean risposta, int idInvito, int idUser) {
        return userService.risponde(risposta, idInvito, idUser);
    }
}
