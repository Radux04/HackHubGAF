package unicam.model.team;

import unicam.model.utenti.user.User;

import java.util.List;

public class Team {
    private int id;
    private String nome;
    private String descrizione;
    private User coordinatore;
    private boolean occupato;
    private List<User> membri;

    public Team(String nome, String descrizione, User coordinatore, List<User> membri) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.coordinatore = coordinatore;
        this.membri = membri;
        this.occupato = false;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public User getCoordinatore() {
        return coordinatore;
    }

    public void setCoordinatore(User coordinatore) {
        this.coordinatore = coordinatore;
    }

    public boolean isOccupato() {
        return occupato;
    }

    public void setOccupato(boolean occupato) {
        this.occupato = occupato;
    }

    public List<User> getMembri() {
        return membri;
    }

    public void setMembri(List<User> membri) {
        this.membri = membri;
    }
}
