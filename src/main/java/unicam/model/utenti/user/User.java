package unicam.model.utenti.user;

import unicam.model.team.Team;
import unicam.model.utenti.Utente;
import unicam.model.utenti.staff.RuoliStaff;

public class User extends Utente {
    private Ruoli ruolo;
    private Team team;


    public User(String username, String password, String email) {
        super(username, password, email);
        this.ruolo = Ruoli.UTENTE;
    }

    public Ruoli getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruoli ruolo) {
        this.ruolo = ruolo;
    }

    public Team getTeam() {
        return this.team;
    }
}
