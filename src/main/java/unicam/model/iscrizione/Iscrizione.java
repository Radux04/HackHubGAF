package unicam.model.iscrizione;

import unicam.model.utenti.user.User;

import java.util.List;

public class Iscrizione {
    private Long id;
    private Long htId;
    private Long teamId;

    public Iscrizione() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHtId() {
        return htId;
    }

    public void setHtId(Long htId) {
        this.htId = htId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
