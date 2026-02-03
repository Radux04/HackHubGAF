package unicam.model.hackathon.mvc;

import unicam.model.hackathon.builder.HackathonBuilder;
import unicam.model.hackathon.entity.DescrizioneHT;
import unicam.model.hackathon.entity.Hackathon;
import unicam.model.hackathon.entity.PlacementHT;
import unicam.model.hackathon.entity.StaffHT;
import unicam.model.hackathon.repo.InMemoryHackathonRepository;
import unicam.model.hackathon.repo.InMemoryStaffRepository;
import unicam.model.utenti.staff.Staff;

public class HackathonService {
    private InMemoryHackathonRepository inMemoryHackathonRepository;
    private InMemoryStaffRepository inMemoryStaffRepository;

    public HackathonService() {
        this.inMemoryHackathonRepository = new InMemoryHackathonRepository();
        this.inMemoryStaffRepository = new InMemoryStaffRepository();
    }

    public Hackathon CreaHackathon(DescrizioneHT descrizione, PlacementHT placement, StaffHT staff, String nome, Staff organizzatore) {
        // Implementation goes here
        if(organizzatore.isOccupato()) throw new IllegalArgumentException("Organizzatore occupato");
        else{
            //crea hackathon
            Staff g = staff.getGiudice();
            if(g.isOccupato())  throw new IllegalArgumentException("Giudice occupato");
            else{
                for(Staff m : staff.getMentori()){
                    if(m.isOccupato()){
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
            for(Staff m : staff.getMentori()){
                m.setOccupato(true);
            }
            organizzatore.setOccupato(true);

            inMemoryStaffRepository.save(organizzatore);
            inMemoryStaffRepository.save(g);
            for(Staff m : staff.getMentori()){
                inMemoryStaffRepository.save(m);
            }

            return inMemoryHackathonRepository.save(hackathon);
        }
    }
}
