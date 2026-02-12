package unicam.service;

import unicam.model.consegna.Consegna;
import unicam.model.hackathon.entity.Sottomissione;
import unicam.repository.ConsegnaRepository;

public class ConsegnaService {

    private final ConsegnaRepository consegnaRepository;

    public ConsegnaService(ConsegnaRepository consegnaRepository) {
        this.consegnaRepository = consegnaRepository;

    }

    public boolean caricaSottomissione(int idSottomissione, String descrizione, int idIscrizione){
        Consegna c = new Consegna(idIscrizione, descrizione, idSottomissione);
        if(consegnaRepository.contains(c)){
            return false;
        }

        this.consegnaRepository.save(c);
        return true;
    }
}
