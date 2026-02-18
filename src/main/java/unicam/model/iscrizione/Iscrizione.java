package unicam.model.iscrizione;

import unicam.model.utenti.user.User;

import java.util.List;

public class Iscrizione {
    private int id;
    private int htId;
    private int teamId;

    public Iscrizione() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHtId() {
        return htId;
    }

    public void setHtId(int htId) {
        this.htId = htId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
