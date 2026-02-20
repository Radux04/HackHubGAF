package unicam.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unicam.dto.RispostaDTO;
import unicam.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/risposta")
    public boolean risponde(@RequestBody RispostaDTO rispostaDTO) {
        return userService.risponde(rispostaDTO);
    }
}
