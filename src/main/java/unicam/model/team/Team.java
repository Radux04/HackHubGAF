package unicam.model.team;

import unicam.model.utenti.user.User;

import java.util.List;

public class Team {
    private int id;
    private String nome;
    private String descrizione;
    private int coordinatore;
    private boolean occupato;
    private List<Integer> membri;

    public Team(String nome, String descrizione, int coordinatore, List<Integer> membri) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.coordinatore = coordinatore;
        this.membri = membri;
        this.occupato = false;
    }

        public Team() {}

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

    public int getCoordinatore() {
        return coordinatore;
    }

    public void setCoordinatore(int coordinatore) {
        this.coordinatore = coordinatore;
    }

    public boolean isOccupato() {
        return occupato;
    }

    public void setOccupato(boolean occupato) {
        this.occupato = occupato;
    }

    public List<Integer> getMembri() {
        return membri;
    }

    public void setMembri(List<Integer> membri) {
        this.membri = membri;
    }
}
