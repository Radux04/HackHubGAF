package unicam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unicam.dto.ConsegnaRequest;
import unicam.service.ConsegnaService;

@RestController
@RequestMapping("/consegna")
@RequiredArgsConstructor
public class ConsegnaController {

    private final ConsegnaService consegnaService;

    @PostMapping
    public boolean caricaSottomissione(@RequestBody ConsegnaRequest sottomissione) {
        return this.consegnaService.caricaSottomissione(sottomissione);
    }

    @DeleteMapping("/{idConsegna}")
    public void ritiraSottomissione(@PathVariable Long idConsegna) {
        this.consegnaService.ritiraSottomissione(idConsegna);
    }
}
