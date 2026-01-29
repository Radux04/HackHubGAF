package unicam.Model;

public class Staff extends Utente {
    private RuoliStaff ruolo;

    public Staff(String username, String password, String email, RuoliStaff ruolo) {
        super(username, password, email);
        this.ruolo = ruolo;
    }

    public RuoliStaff getRuolo() {
        return ruolo;
    }

    public void setRuolo(RuoliStaff ruolo) {
        this.ruolo = ruolo;
    }
}
