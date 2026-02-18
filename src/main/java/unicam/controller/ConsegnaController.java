package unicam.controller;

import org.springframework.web.bind.annotation.*;
import unicam.dto.consegna.ConsegnaRequest;
import unicam.service.ConsegnaService;

@RestController
@RequestMapping("/consegna")
public class ConsegnaController {

    private final ConsegnaService consegnaService;

    public ConsegnaController(ConsegnaService consegnaService) {
        this.consegnaService = consegnaService;
    }

    @PostMapping
    public boolean caricaSottomissione(@RequestBody ConsegnaRequest sottomissione) {
        return this.consegnaService.caricaSottomissione(sottomissione);
    }

    @DeleteMapping("/{idConsegna}")
    public void ritiraSottomissione(@RequestBody Long idConsegna) {
        this.consegnaService.ritiraSottomissione(idConsegna);
    }
}
