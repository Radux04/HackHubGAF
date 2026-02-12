package unicam.model.hackathon.entity;

import unicam.model.utenti.staff.Staff;

import java.util.ArrayList;
import java.util.List;

public class Hackathon {
    private int id;
    private String nome;
    private DescrizioneHT descrizione;
    private PlacementHT placement;
    private StaffHT staff;
    private StatiHackathon stato;
    private int idOrganizzatore;
    private List<Integer> sottomissioni;

    public Hackathon() {}

    public Hackathon(String nome, DescrizioneHT descrizione, PlacementHT placement, StaffHT staff, StatiHackathon stato, int idOrganizzatore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.placement = placement;
        this.staff = staff;
        this.stato = stato;
        this.idOrganizzatore = idOrganizzatore;
        this.sottomissioni = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public DescrizioneHT getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(DescrizioneHT descrizione) {
        this.descrizione = descrizione;
    }

    public PlacementHT getPlacement() {
        return placement;
    }

    public void setPlacement(PlacementHT placement) {
        this.placement = placement;
    }

    public StaffHT getStaff() {
        return staff;
    }

    public void setStaff(StaffHT staff) {
        this.staff = staff;
    }

    public StatiHackathon getStato() {
        return stato;
    }

    public void setStato(StatiHackathon stato) {
        this.stato = stato;
    }

    public int getIdOrganizzatore() {
        return idOrganizzatore;
    }

    public void setIdOrganizzatore(int idOrganizzatore) {
        this.idOrganizzatore = idOrganizzatore;
    }

    public List<Integer> getSottomissioni() {
        return sottomissioni;
    }

    public void setSottomissioni(List<Integer> sottomissioni) {
        this.sottomissioni = sottomissioni;
    }
}
