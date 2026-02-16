package unicam.service;

import unicam.model.hackathon.builder.HackathonBuilder;
import unicam.model.hackathon.entity.*;
import unicam.repository.*;
import unicam.model.supporto.RichiestaSupporto;
import unicam.model.utenti.staff.Staff;
import java.util.List;

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

    public Hackathon CreaHackathon(DescrizioneHT descrizione, PlacementHT placement, StaffHT staff, String nome, int idOrganizzatore) {

        Staff s =  inMemoryStaffRepository.findStaff(idOrganizzatore);

        //se l'organizzatore è già occupato in un altro h
        if (s.isOccupato()) throw new IllegalArgumentException("Organizzatore occupato");
        //se l'organizzatore è libero
        else {
            Staff g = inMemoryStaffRepository.findStaff(staff.getIdGiudice());
            //se il giudice è occupato
            if (g.isOccupato()) throw new IllegalArgumentException("Giudice occupato");
            //se il giudice è libero
            else {
                //cicla tutti i mentori
                for (int m : staff.getMentori()) {
                    //se uno dei mentori è già occupato, lancia un'eccezione
                    if (inMemoryStaffRepository.findStaff(m).isOccupato()) {
                        throw new IllegalArgumentException("un mentore è occupato");
                    }
                }

                //l'hackathon viene creato attraverso il builder
                HackathonBuilder hackathonBuilder = new HackathonBuilder();
                hackathonBuilder.buildName(nome)
                        .buildDescrizione(descrizione)
                        .buildPlacement(placement)
                        .buildStaff(staff)
                        .buildOrganizzatore(idOrganizzatore);
                Hackathon hackathon = hackathonBuilder.build();


                g.setOccupato(true);
                for (int m : staff.getMentori()) {
                    //setta lo stato del mentore a occupato
                    inMemoryStaffRepository.findStaff(m).setOccupato(true);
                    //setta l'attributo hackathon del mentore come l'hackathon appena creato
                    inMemoryStaffRepository.findStaff(m).setIdHackathon(hackathon.getId());
                }

                s.setOccupato(true);
                s.setIdHackathon(hackathon.getId());

                inMemoryStaffRepository.save(s);
                inMemoryStaffRepository.save(g);
                for (int m : staff.getMentori()) {
                    inMemoryStaffRepository.save(inMemoryStaffRepository.findStaff(m));
                }

                return inMemoryHackathonRepository.save(hackathon);
            }

        }
    }

    public boolean richiediSupporto(int idTeam, String descrizione) {
        int hackathonId = inMemoryIscrizioniRepository.getHackatonByTeam(idTeam);
        RichiestaSupporto richiesta = new RichiestaSupporto(idTeam, descrizione, hackathonId);
        return richiestaSupportoRepository.save(richiesta) != null;
    }


    public List<RichiestaSupporto> visualizzaRichiesteSupporto(int idHackathon) {

        return richiestaSupportoRepository.findByHackathonId(idHackathon);

    }

    public void creaSottomissione(String descrizione, String titolo, int idHackathon) {
        if(inMemoryHackathonRepository.getHackathonById(idHackathon).getSottomissioni().size() == 10)
            throw new IllegalArgumentException("Limite massimo di sottomissioni raggiunto");

        Sottomissione sottomissione = new Sottomissione(titolo, descrizione);
        inMemoryHackathonRepository.getHackathonById(idHackathon).getSottomissioni().add(sottomissione.getId());
    }
}