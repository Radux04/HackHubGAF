package unicam.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import unicam.dto.hackathon.DescrizioneHT;
import unicam.dto.hackathon.HackathonRequest;
import unicam.dto.hackathon.PlacementHT;
import unicam.dto.hackathon.StaffHT;
import unicam.model.hackathon.builder.HackathonBuilder;
import unicam.model.hackathon.entity.*;
import unicam.repository.*;
import unicam.model.supporto.RichiestaSupporto;
import unicam.model.utenti.staff.Staff;
import java.util.List;

@Service
public class HackathonService {
    private final HackathonRepository inMemoryHackathonRepository;
    private final StaffRepository inMemoryStaffRepository;
    private final RichiestaSupportoRepository richiestaSupportoRepository;
    private final IscrizioneRepository inMemoryIscrizioniRepository;

    public HackathonService(InMemoryHackathonRepository inMemoryHackathonRepository, InMemoryStaffRepository inMemoryStaffRepository, InMemoryRichiestaSupportoRepository inMemoryRichiestaSupportoRepository, InMemoryIscrizioniRepository inMemoryIscrizioniRepository) {
        this.inMemoryHackathonRepository = inMemoryHackathonRepository;
        this.inMemoryStaffRepository = inMemoryStaffRepository;
        this.richiestaSupportoRepository = inMemoryRichiestaSupportoRepository;
        this.inMemoryIscrizioniRepository = inMemoryIscrizioniRepository;
    }

    public Hackathon CreaHackathon(HackathonRequest hackathonRequest) {

        DescrizioneHT descrizione = hackathonRequest.getDescrizione();
        PlacementHT placement = hackathonRequest.getPlacement();
        StaffHT staff = hackathonRequest.getStaff();
        String nome = hackathonRequest.getNome();
        Long idOrganizzatore = hackathonRequest.getIdOrganizzatore();

        Staff organizzatore = inMemoryStaffRepository.findById(idOrganizzatore).get();
        Staff giudice = inMemoryStaffRepository.findById(staff.getIdGiudice()).get();
        List<Staff> mentori = staff.getMentori().stream().map(m -> inMemoryStaffRepository.findById(m).get()).toList();


        //se l'organizzatore è già occupato in un altro h
        if (organizzatore.isOccupato()) throw new IllegalArgumentException("Organizzatore occupato");
        //se l'organizzatore è libero
        else {
            //se il giudice è occupato
            if (giudice.isOccupato()) throw new IllegalArgumentException("Giudice occupato");
            //se il giudice è libero
            else {
                //cicla tutti i mentori
                for (Staff m : mentori) {
                    //se uno dei mentori è già occupato, lancia un'eccezione
                    if (m.isOccupato()) {
                        throw new IllegalArgumentException("un mentore è occupato");
                    }
                }

                //l'hackathon viene creato attraverso il builder
                HackathonBuilder hackathonBuilder = new HackathonBuilder();
                hackathonBuilder.buildName(nome)
                        .buildDataFine(placement.getDataInizio())
                        .buildDataFine(placement.getDataFine())
                        .buildScadenzaIscrizioni(placement.getScadenzaIscrizioni())
                        .buildGiudice(giudice)
                        .buildLuogo(placement.getLuogo())
                        .buildMaxSize(descrizione.getMaxSize())
                        .buildMentori(mentori)
                        .buildOrganizzatore(organizzatore)
                        .buildPremio(descrizione.getPremio())
                        .buildRegolamento(descrizione.getRegolamento());
                Hackathon hackathon = hackathonBuilder.build();


                giudice.setOccupato(true);
                for (Staff m : mentori) {
                    //setta lo stato del mentore a occupato
                    m.setOccupato(true);
                    //setta l'attributo hackathon del mentore come l'hackathon appena creato
                    m.setHackathon(hackathon);
                }

                organizzatore.setOccupato(true);
                organizzatore.setHackathon(hackathon);

                inMemoryStaffRepository.save(organizzatore);
                inMemoryStaffRepository.save(giudice);
                inMemoryStaffRepository.saveAll(mentori);

                return inMemoryHackathonRepository.save(hackathon);
            }

        }
    }

    public RichiestaSupporto richiediSupporto(Long idTeam, String descrizione) {
        Long hackathonId = inMemoryIscrizioniRepository.getHackatonByTeam(idTeam);
        RichiestaSupporto richiesta = new RichiestaSupporto(idTeam, descrizione, hackathonId);
        return richiestaSupportoRepository.save(richiesta);
    }


    public List<RichiestaSupporto> visualizzaRichiesteSupporto(Long idHackathon) {

        return richiestaSupportoRepository.findByHackathonId(idHackathon);

    }

    public void creaSottomissione(String descrizione, String titolo, Long idHackathon) {
        if(inMemoryHackathonRepository.getHackathonById(idHackathon).getSottomissioni().size() == 10)
            throw new IllegalArgumentException("Limite massimo di sottomissioni raggiunto");

        Sottomissione sottomissione = new Sottomissione(titolo, descrizione);
        inMemoryHackathonRepository.getHackathonById(idHackathon).getSottomissioni().add(sottomissione.getId());
    }
}