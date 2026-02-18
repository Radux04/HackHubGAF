package unicam.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unicam.dto.user.RispostaDTO;
import unicam.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
@PostMapping("/risposta")
    public boolean risponde(@RequestBody RispostaDTO rispostaDTO) {
        return userService.risponde(rispostaDTO);
    }
}
