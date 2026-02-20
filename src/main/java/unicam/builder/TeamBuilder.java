package unicam.builder;

import unicam.model.Team;
import unicam.model.User;

import java.util.ArrayList;

public class TeamBuilder {
    private Team team;

    public TeamBuilder() {
        reset();
    }

    public TeamBuilder buildNome(String nome) {
        team.setNome(nome);
        return this;
    }

    public TeamBuilder buildDescrizione(String descrizione) {
        team.setDescrizione(descrizione);
        return this;
    }

    public TeamBuilder buildCoordinatore(User coordinatore) {
        team.setCoordinatore(coordinatore);
        return this;
    }

    public TeamBuilder reset(){
        team = new Team();
        return this;
    }

    public Team build() {
        team.setMembri(new ArrayList<>());
        team.setOccupato(false);
        return team;
    }
}