package unicam.controller;


import org.springframework.web.bind.annotation.RequestBody;
import unicam.dto.user.RispostaDTO;
import unicam.service.UserService;


public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public boolean risponde(@RequestBody RispostaDTO rispostaDTO) {
        return userService.risponde(rispostaDTO);
    }
}
