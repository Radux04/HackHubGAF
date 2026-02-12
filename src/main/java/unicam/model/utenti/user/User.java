package unicam.model.utenti.user;

import unicam.model.team.Team;
import unicam.model.utenti.Utente;
import unicam.model.utenti.staff.RuoliStaff;

public class User extends Utente {
    private Ruoli ruolo;
    private int idTeam;


    public User(String username, String password, String email) {
        super(username, password, email);
        this.ruolo = Ruoli.UTENTE;
        idTeam = -1;
    }

    public Ruoli getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruoli ruolo) {
        this.ruolo = ruolo;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }
}
