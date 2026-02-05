package unicam.model.hackathon.mvc;

import unicam.model.hackathon.builder.HackathonBuilder;
import unicam.model.hackathon.entity.DescrizioneHT;
import unicam.model.hackathon.entity.Hackathon;
import unicam.model.hackathon.entity.PlacementHT;
import unicam.model.hackathon.entity.StaffHT;
import unicam.model.hackathon.repo.InMemoryHackathonRepository;
import unicam.model.hackathon.repo.InMemoryStaffRepository;
import unicam.model.iscrizione.repo.InMemoryIscrizioniRepository;
import unicam.model.supporto.RichiestaSupporto;
import unicam.model.supporto.repository.InMemoryRichiestaSupportoRepository;
import unicam.model.supporto.repository.RichiestaSupportoRepository;
import unicam.model.team.Team;
import unicam.model.utenti.staff.Staff;
import unicam.model.utenti.user.User;

import java.util.List;

public class HackathonService {
    private final InMemoryHackathonRepository inMemoryHackathonRepository;
    private final InMemoryStaffRepository inMemoryStaffRepository;
    private final RichiestaSupportoRepository richiestaSupportoRepository;
    private final InMemoryIscrizioniRepository inMemoryIscrizioniRepository;

    public HackathonService() {
        this.inMemoryHackathonRepository = new InMemoryHackathonRepository();
        this.inMemoryStaffRepository = new InMemoryStaffRepository();
        this.richiestaSupportoRepository = new InMemoryRichiestaSupportoRepository();
        this.inMemoryIscrizioniRepository = new InMemoryIscrizioniRepository();
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
            }
            organizzatore.setOccupato(true);

            inMemoryStaffRepository.save(organizzatore);
            inMemoryStaffRepository.save(g);
            for (Staff m : staff.getMentori()) {
                inMemoryStaffRepository.save(m);
            }

            return inMemoryHackathonRepository.save(hackathon);
        }
    }

    public boolean richiediSupporto(Team team, User utente, String descrizione) {
        if (team == null) {
            throw new IllegalArgumentException("Team non valido");
        }
        if (utente == null) {
            throw new IllegalArgumentException("Utente non valido");
        }
        if (descrizione == null || descrizione.isBlank()) {
            throw new IllegalArgumentException("Descrizione non valida");
        }
        if (!utenteInTeam(team, utente)) {
            throw new IllegalArgumentException("Utente non appartiene al team");
        }

        int hackathonId = getHackatonByTeam(team);
        if (hackathonId < 0) {
            throw new IllegalArgumentException("Hackaton non valido");
        }

        RichiestaSupporto richiesta = new RichiestaSupporto(
                team.getId(),
                descrizione,
                hackathonId
        );
        return richiestaSupportoRepository.save(richiesta) != null;
    }

    private int getHackatonByTeam(Team team) {
        return inMemoryIscrizioniRepository.getHackatonByTeam(team);
    }

    private boolean utenteInTeam(Team team, User utente) {
        if (team.getCoordinatore() != null && team.getCoordinatore().getId() == utente.getId()) {
            return true;
        }
        List<User> membri = team.getMembri();
        if (membri == null) {
            return false;
        }
        for (User m : membri) {
            if (m != null && m.getId() == utente.getId()) {
                return true;
            }
        }
        return false;
    }
}