package unicam.model.utenti.staff;

import unicam.model.hackathon.entity.Hackathon;
import unicam.model.utenti.Utente;

public class Staff extends Utente {
    private RuoliStaff ruolo;
    private int idHackathon;

    public Staff(String username, String password, String email, RuoliStaff ruolo) {
        super(username, password, email);
        this.ruolo = ruolo;
        idHackathon = -1;
    }

    public RuoliStaff getRuolo() {
        return ruolo;
    }

    public void setRuolo(RuoliStaff ruolo) {
        this.ruolo = ruolo;
    }

    public int getIdHackathon() {
        return idHackathon;
    }

    public void setIdHackathon(int idHackathon) {
        this.idHackathon = idHackathon;
    }
}
