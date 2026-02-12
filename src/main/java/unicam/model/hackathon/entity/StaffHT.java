package unicam.model.hackathon.entity;

import unicam.model.utenti.staff.Staff;

import java.util.List;

public class StaffHT {
    private int idGiudice;
    private List<Integer> mentori;

    public StaffHT(int idGiudice, List<Integer> mentori) {
        this.idGiudice = idGiudice;
        this.mentori = mentori;
    }

    public int getIdGiudice() {
        return idGiudice;
    }

    public void setIdGiudice(int idGiudice) {
        this.idGiudice = idGiudice;
    }

    public List<Integer> getMentori() {
        return mentori;
    }

    public void setMentori(List<Integer> mentori) {
        this.mentori = mentori;
    }

}
