package unicam.model.inviti;

public class Invito {
    private Long id;
    private Long teamId;
    private Long destinatario;

    public Invito(Long teamId, Long destinatario) {
        this.teamId = teamId;
        this.destinatario = destinatario;
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

    public Long getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Long destinatario) {
        this.destinatario = destinatario;
    }
}
