package unicam.controller;

import unicam.model.hackathon.entity.Sottomissione;
import unicam.repository.ConsegnaRepository;
import unicam.service.ConsegnaService;

public class ConsegnaController {

    private final ConsegnaService consegnaService;

    public ConsegnaController(ConsegnaService consegnaService) {
        this.consegnaService = consegnaService;
    }

    public boolean caricaSottomissione(int idSottomissione, String descrizione, int idIscrizione){
        return this.consegnaService.caricaSottomissione(idSottomissione, descrizione, idIscrizione);

    }
}
