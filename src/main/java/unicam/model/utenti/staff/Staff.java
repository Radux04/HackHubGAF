package unicam.model.utenti.staff;

import unicam.model.hackathon.entity.Hackathon;
import unicam.model.utenti.Utente;

public class Staff extends Utente {
    private RuoliStaff ruolo;
    private Hackathon ht;

    public Staff(String username, String password, String email, RuoliStaff ruolo) {
        super(username, password, email);
        this.ruolo = ruolo;
        ht = null;
    }

    public RuoliStaff getRuolo() {
        return ruolo;
    }

    public void setRuolo(RuoliStaff ruolo) {
        this.ruolo = ruolo;
    }

    public Hackathon getHt() {
        return ht;
    }

    public void setHt(Hackathon ht) {
        this.ht = ht;
    }
}
