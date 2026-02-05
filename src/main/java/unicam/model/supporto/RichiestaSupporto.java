package unicam.model.supporto;

public class RichiestaSupporto {
    private int id;
    private int teamId;
    private String descrizione;
    private int hackathonId;

    public RichiestaSupporto(int teamId, String descrizione, int hackathonId) {
        this.teamId = teamId;
        this.descrizione = descrizione;
        this.hackathonId = hackathonId;
    }

    public RichiestaSupporto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getHackathonId() {
        return hackathonId;
    }

    public void setHackathonId(int hackathonId) {
        this.hackathonId = hackathonId;
    }
}