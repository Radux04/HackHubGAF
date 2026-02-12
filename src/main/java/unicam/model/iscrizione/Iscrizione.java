package unicam.model.iscrizione;

import unicam.model.utenti.user.User;

import java.util.List;

public class Iscrizione {
    private int id;
    private int htId;
    private int teamId;
    private List<Integer> participanti;

    public Iscrizione(int htId, int teamId, List<Integer> participanti) {
        this.htId = htId;
        this.teamId = teamId;
        this.participanti = participanti;
    }

    public Iscrizione() {

    }

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

    public List<Integer> getParticipant() {
        return participanti;
    }

    public void setParticipant(List<Integer> participanti) {
        this.participanti = participanti;
    }
}
