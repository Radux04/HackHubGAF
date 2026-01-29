package unicam.model.iscrizione;

import java.util.List;

public class Iscrizione {
    private int id;
    private int htId;
    private int teamId;
    private List<Integer> participant;

    public Iscrizione(int htId, int teamId, List<Integer> participant) {
        this.htId = htId;
        this.teamId = teamId;
        this.participant = participant;
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
        return participant;
    }

    public void setParticipant(List<Integer> participant) {
        this.participant = participant;
    }
}
