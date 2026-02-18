package unicam.model.hackathon.builder;

import unicam.model.hackathon.entity.*;

import java.util.ArrayList;

public class HackathonBuilder {
    private Hackathon hackathon = new Hackathon();

    public HackathonBuilder() {
        reset();
    }

    public HackathonBuilder buildName(String name) {
        hackathon.setNome(name);
        return this;
    }

    public HackathonBuilder buildDescrizione(DescrizioneHT description) {
        hackathon.setDescrizione(description);
        return this;
    }

    public HackathonBuilder buildPlacement(PlacementHT placementHT) {
        hackathon.setPlacement(placementHT);
        return this;
    }

    public HackathonBuilder buildStaff(StaffHT staffHT) {
        hackathon.setStaff(staffHT);
        return this;
    }

    public HackathonBuilder buildOrganizzatore(int org) {
        hackathon.setIdOrganizzatore(org);
        return this;
    }

    public Hackathon build(){
        hackathon.setStato(StatiHackathon.IN_ISCRIZIONE);
        hackathon.setSottomissioni(new ArrayList<>());
        return hackathon;
    }

    public HackathonBuilder reset() {
        hackathon = new Hackathon();
        return this;
    }


}
