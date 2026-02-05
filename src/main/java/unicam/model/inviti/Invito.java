package unicam.model.inviti;

public class Invito {
    private int id;
    private int teamId;
    private int destinatario;

    public Invito(int teamId, int destinatario) {
        this.teamId = teamId;
        this.destinatario = destinatario;
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

    public int getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(int destinatario) {
        this.destinatario = destinatario;
    }
}
