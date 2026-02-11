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
    private Staff organizzatore;
    public List<Sottomissione> sottomissioni;

    public Hackathon() {}

    public Hackathon(String nome, DescrizioneHT descrizione, PlacementHT placement, StaffHT staff, StatiHackathon stato, Staff organizzatore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.placement = placement;
        this.staff = staff;
        this.stato = stato;
        this.organizzatore = organizzatore;
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

    public Staff getOrganizzatore() {
        return organizzatore;
    }

    public void setOrganizzatore(Staff organizzatore) {
        this.organizzatore = organizzatore;
    }

    public List<Sottomissione> getSottomissioni() {
        return sottomissioni;
    }

    public void setSottomissioni(List<Sottomissione> sottomissioni) {
        this.sottomissioni = sottomissioni;
    }
}
