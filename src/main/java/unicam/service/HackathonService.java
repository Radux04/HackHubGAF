package unicam.service;

import unicam.model.hackathon.builder.HackathonBuilder;
import unicam.model.hackathon.entity.*;
import unicam.model.utenti.Utente;
import unicam.repository.*;
import unicam.model.supporto.RichiestaSupporto;
import unicam.model.team.Team;
import unicam.model.utenti.staff.Staff;
import unicam.model.utenti.user.User;

import java.io.File;
import java.util.List;

public class HackathonService {
    private final HackathonRepository inMemoryHackathonRepository;
    private final StaffRepository inMemoryStaffRepository;
    private final RichiestaSupportoRepository richiestaSupportoRepository;
    private final IscrizioneRepository inMemoryIscrizioniRepository;
    private final TeamRepository inMemoryTeamRepository;

    public HackathonService() {
        this.inMemoryHackathonRepository = new InMemoryHackathonRepository();
        this.inMemoryStaffRepository = new InMemoryStaffRepository();
        this.richiestaSupportoRepository = new InMemoryRichiestaSupportoRepository();
        this.inMemoryIscrizioniRepository = new InMemoryIscrizioniRepository();
        this.inMemoryTeamRepository = new InMemoryTeamRepository();
    }

    public Hackathon CreaHackathon(DescrizioneHT descrizione, PlacementHT placement, StaffHT staff, String nome, Staff organizzatore) {
        if (organizzatore.isOccupato()) throw new IllegalArgumentException("Organizzatore occupato");
        else {
            Staff g = staff.getGiudice();
            if (g.isOccupato()) throw new IllegalArgumentException("Giudice occupato");
            else {
                for (Staff m : staff.getMentori()) {
                    if (m.isOccupato()) {
                        throw new IllegalArgumentException("Mentore occupato: " + m.getUsername());
                    }
                }
            }
            HackathonBuilder hackathonBuilder = new HackathonBuilder();
            hackathonBuilder.buildName(nome)
                    .buildDescrizione(descrizione)
                    .buildPlacement(placement)
                    .buildStaff(staff)
                    .buildOrganizzatore(organizzatore);
            Hackathon hackathon = hackathonBuilder.build();

            g.setOccupato(true);
            for (Staff m : staff.getMentori()) {
                m.setOccupato(true);
                m.setHt(hackathon);
            }
            organizzatore.setOccupato(true);
            organizzatore.setHt(hackathon);

            inMemoryStaffRepository.save(organizzatore);
            inMemoryStaffRepository.save(g);
            for (Staff m : staff.getMentori()) {
                inMemoryStaffRepository.save(m);
            }

            return inMemoryHackathonRepository.save(hackathon);
        }
    }

    public boolean richiediSupporto(int idTeam, String descrizione) {

        int hackathonId = this.getHackatonByTeam(idTeam);
        Hackathon hackathon =  inMemoryHackathonRepository.getHackathonById(hackathonId);

        RichiestaSupporto richiesta = new RichiestaSupporto(idTeam, descrizione, hackathonId);
        return richiestaSupportoRepository.save(richiesta) != null;
    }

    private int getHackatonByTeam(int idTeam) {
        return inMemoryIscrizioniRepository.getHackatonByTeam(idTeam);
    }

    private boolean utenteInTeam(int idTeam, int idUtente) {
        Team team = inMemoryTeamRepository.getTeamById(idTeam);
        Utente utente = team.getMembri().get(idUtente);

        List<User> membri = team.getMembri();
        if (membri == null) {
            return false;
        }
        for (User m : membri) {
            if (m.getId() == utente.getId()) {
                return true;
            }
        }
        return false;
    }


    public List<RichiestaSupporto> visualizzaRichiesteSupporto(int idHackathon) {

        return richiestaSupportoRepository.findByHackathonId(idHackathon);

    }

    public void creaSottomissione(String descrizione, String titolo, int idHackathon) {
        if(inMemoryHackathonRepository.getHackathonById(idHackathon).getSottomissioni().size() == 10)
            throw new IllegalArgumentException("Limite massimo di sottomissioni raggiunto");

        Sottomissione sottomissione = new Sottomissione(titolo, descrizione);
        inMemoryHackathonRepository.getHackathonById(idHackathon).getSottomissioni().add(sottomissione);
        inMemoryHackathonRepository.save(inMemoryHackathonRepository.getHackathonById(idHackathon));
    }
}