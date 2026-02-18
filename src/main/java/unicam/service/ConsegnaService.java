package unicam.service;

import org.hibernate.type.SpecialOneToOneType;
import org.springframework.stereotype.Service;
import unicam.dto.ConsegnaRequest;
import unicam.model.consegna.Consegna;
import unicam.model.hackathon.entity.Sottomissione;
import unicam.model.iscrizione.Iscrizione;
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
        Optional<Iscrizione> i = this.iscrizioneRepository.findById(sottomissione.getIdIscrizione());
        Optional<Sottomissione> s = this.sottomissioniRepository.findById(sottomissione.getIdSottomissione());


        if(i.isPresent() && s.isPresent()){
            Consegna c = new Consegna(sottomissione.getDescrizione(), s.get(), i.get());

            //controllo se esiste già una sottomissione inviata dallo stesso team e che risponde alla stessa consegna
            for(Consegna co : consegnaRepository.findAll()){
                if(co.getIscrizione().equals(c.getIscrizione()) && co.getSottomissione().equals(c.getSottomissione())) return false;
            }

            this.consegnaRepository.save(c);
            return true;
        }

    }

    public void ritiraSottomissione(Long idConsegna) {
        consegnaRepository.remove(idConsegna);

    }
}
