package unicam.Model;

public class Hackathon {
    private int id;
    private String nome;
    private DescrizioneHT descrizione;
    private PlacementHT placement;
    private StaffHT staff;
    private StatiHackathon stato;
    private Staff organizzatore;

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
}
