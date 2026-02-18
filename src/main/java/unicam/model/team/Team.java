package unicam.model.team;

import unicam.model.utenti.user.User;

import java.util.List;

public class Team {
    private Long id;
    private String nome;
    private String descrizione;
    private Long coordinatore;
    private boolean occupato;
    private List<Long> membri;

    public Team(String nome, String descrizione, Long coordinatore, List<Long> membri) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.coordinatore = coordinatore;
        this.membri = membri;
        this.occupato = false;
    }

        public Team() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getCoordinatore() {
        return coordinatore;
    }

    public void setCoordinatore(Long coordinatore) {
        this.coordinatore = coordinatore;
    }

    public boolean isOccupato() {
        return occupato;
    }

    public void setOccupato(boolean occupato) {
        this.occupato = occupato;
    }

    public List<Long> getMembri() {
        return membri;
    }

    public void setMembri(List<Long> membri) {
        this.membri = membri;
    }
}
