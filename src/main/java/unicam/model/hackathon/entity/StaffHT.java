package unicam.model.hackathon.entity;

import unicam.model.utenti.staff.Staff;

import java.util.List;

public class StaffHT {
    private Long idGiudice;
    private List<Long> mentori;

    public StaffHT(Long idGiudice, List<Integer> mentori) {
        this.idGiudice = idGiudice;
        this.mentori = mentori;
    }

    public Long getIdGiudice() {
        return idGiudice;
    }

    public void setIdGiudice(Long idGiudice) {
        this.idGiudice = idGiudice;
    }

    public List<Integer> getMentori() {
        return mentori;
    }

    public void setMentori(List<Integer> mentori) {
        this.mentori = mentori;
    }

}
