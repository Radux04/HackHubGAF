package unicam.model.team.builder;

import unicam.model.team.Team;
import unicam.model.utenti.user.User;

import java.util.ArrayList;
import java.util.List;

public class TeamBuilder {
    private Team team;

    public TeamBuilder() {
//        this.team = new Team(); // serve costruttore vuoto in Team
//        this.team.setMembri(new ArrayList<>());
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

    public TeamBuilder buildCoordinatore(int coordinatore) {
        team.setCoordinatore(coordinatore);
        return this;
    }

    public TeamBuilder reset(){
        team = new Team();
        return this;
    }

    public Team build() {
        team.setMembri(new ArrayList<>());
        return team;
    }
}