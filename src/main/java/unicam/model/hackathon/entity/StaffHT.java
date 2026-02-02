package unicam.model.hackathon.entity;

import unicam.model.utenti.staff.Staff;

import java.util.List;

public class StaffHT {
    private Staff giudice;
    private List<Staff> mentori;

    public Staff getGiudice() {
        return giudice;
    }

    public void setGiudice(Staff giudice) {
        this.giudice = giudice;
    }

    public List<Staff> getMentori() {
        return mentori;
    }

    public void setMentori(List<Staff> mentori) {
        this.mentori = mentori;
    }
}
