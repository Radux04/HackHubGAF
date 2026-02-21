package unicam.service;

import org.springframework.stereotype.Service;
import unicam.dto.ConsegnaRequest;
import unicam.model.Consegna;
import unicam.model.Sottomissione;
import unicam.model.Iscrizione;
import unicam.repository.ConsegnaRepository;
import unicam.repository.IscrizioneRepository;
import unicam.repository.SottomissioniRepository;

import java.util.Optional;

@Service
public class ConsegnaService {

    private final ConsegnaRepository consegnaRepository;
    private final IscrizioneRepository iscrizioneRepository;
    private final SottomissioniRepository sottomissioniRepository;

    public ConsegnaService(ConsegnaRepository consegnaRepository,  IscrizioneRepository iscrizioneRepository, SottomissioniRepository sottomissioniRepository) {
        this.consegnaRepository = consegnaRepository;
        this.iscrizioneRepository = iscrizioneRepository;
        this.sottomissioniRepository = sottomissioniRepository;
    }

    public boolean caricaSottomissione(ConsegnaRequest sottomissione){
        Iscrizione i = this.iscrizioneRepository.findById(sottomissione.idIscrizione()).get();
        Sottomissione s = this.sottomissioniRepository.findById(sottomissione.idSottomissione()).get();

        Consegna c = new Consegna(sottomissione.descrizione(), s, i);

        //controllo se esiste già una sottomissione inviata dallo stesso team e che risponde alla stessa consegna
        for(Consegna co : consegnaRepository.findAll()){
            if(co.getIscrizione().equals(c.getIscrizione()) && co.getSottomissione().equals(c.getSottomissione())) return false;
        }

        this.consegnaRepository.save(c);
        return true;
    }

    public void ritiraSottomissione(Long idConsegna) {
        consegnaRepository.delete(consegnaRepository.findById(idConsegna).get());
    }
}
