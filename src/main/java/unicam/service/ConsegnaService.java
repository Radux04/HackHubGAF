package unicam.service;

import unicam.model.consegna.Consegna;
import unicam.repository.ConsegnaRepository;

public class ConsegnaService {

    private final ConsegnaRepository consegnaRepository;

    public ConsegnaService(ConsegnaRepository consegnaRepository) {
        this.consegnaRepository = consegnaRepository;

    }

    public boolean caricaSottomissione(Long idSottomissione, String descrizione, Long idIscrizione){
        Consegna c = new Consegna(idIscrizione, descrizione, idSottomissione);
        if(consegnaRepository.contains(c)){
            return false;
        }

        this.consegnaRepository.save(c);
        return true;
    }

    public void ritiraSottomissione(int idConsegna) {
        consegnaRepository.remove(idConsegna);

    }
}
