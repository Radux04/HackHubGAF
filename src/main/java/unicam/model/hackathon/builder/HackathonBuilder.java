package unicam.model.hackathon.builder;

import unicam.model.hackathon.entity.*;
import unicam.model.utenti.staff.Staff;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HackathonBuilder {
    private Hackathon hackathon = new Hackathon();

    public HackathonBuilder() {
        reset();
    }

    public HackathonBuilder buildName(String name) {
        hackathon.setNome(name);
        return this;
    }

    public HackathonBuilder buildRegolamento(String regolamento) {
        hackathon.setRegolamento(regolamento);
        return this;
    }

    public HackathonBuilder buildPremio(float premio) {
        hackathon.setPremio(premio);
        return this;
    }

    public HackathonBuilder buildMaxSize(int maxSize) {
        hackathon.setMaxSize(maxSize);
        return this;
    }

    public HackathonBuilder buildScadenzaIscrizioni(String scadenza) {
        hackathon.setScadenzaIscrizioni(scadenza);
        return this;
    }

    public HackathonBuilder buildDataInizio(String dataInizio) {
        hackathon.setDataInizio(dataInizio);
        return this;
    }

    public HackathonBuilder buildDataFine(String dataFine) {
        hackathon.setDataFine(dataFine);
        return this;
    }

    public HackathonBuilder buildLuogo(String luogo) {
        hackathon.setLuogo(luogo);
        return this;
    }

    public HackathonBuilder buildGiudice(Staff giudice) {
        hackathon.setGiudice(giudice);
        return this;
    }

    public HackathonBuilder buildMentori(List<Staff> mentori) {
        hackathon.setMentori(mentori);
        return this;
    }

    public HackathonBuilder buildOrganizzatore(Staff organizzatore) {
        hackathon.setOrganizzatore(organizzatore);
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
