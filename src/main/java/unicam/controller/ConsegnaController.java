package unicam.controller;

import unicam.service.ConsegnaService;

public class ConsegnaController {

    private final ConsegnaService consegnaService;

    public ConsegnaController(ConsegnaService consegnaService) {
        this.consegnaService = consegnaService;
    }

    public boolean caricaSottomissione(Long idSottomissione, String descrizione, Long idIscrizione){
        return this.consegnaService.caricaSottomissione(idSottomissione, descrizione, idIscrizione);
    }


    public void ritiraSottomissione(Long idConsegna) {
        this.consegnaService.ritiraSottomissione(idConsegna);
    }
}
