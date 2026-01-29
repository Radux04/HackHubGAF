package unicam.model.utenti.user;

import unicam.model.utenti.Utente;
import unicam.model.utenti.staff.RuoliStaff;

public class User extends Utente {
    private Ruoli ruolo;


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
}
