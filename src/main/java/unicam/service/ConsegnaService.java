package unicam.service;

import unicam.dto.ConsegnaRequest;
import unicam.model.consegna.Consegna;
import unicam.repository.ConsegnaRepository;

public class ConsegnaService {

    private final ConsegnaRepository consegnaRepository;

    public ConsegnaService(ConsegnaRepository consegnaRepository) {
        this.consegnaRepository = consegnaRepository;

    }

    public boolean caricaSottomissione(ConsegnaRequest sottomissione){



        Consegna c = new Consegna(sottomissione.get, descrizione, idSottomissione);
        if(consegnaRepository.contains(c)){
            return false;
        }

        this.consegnaRepository.save(c);
        return true;
    }

    public void ritiraSottomissione(Long idConsegna) {
        consegnaRepository.remove(idConsegna);

    }
}
