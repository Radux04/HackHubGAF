package unicam.model.supporto;

public class RichiestaSupporto {
    private Long id;
    private Long teamId;
    private String descrizione;
    private Long hackathonId;

    public RichiestaSupporto(Long teamId, String descrizione, Long hackathonId) {
        this.teamId = teamId;
        this.descrizione = descrizione;
        this.hackathonId = hackathonId;
    }

    public RichiestaSupporto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Long getHackathonId() {
        return hackathonId;
    }

    public void setHackathonId(Long hackathonId) {
        this.hackathonId = hackathonId;
    }
}