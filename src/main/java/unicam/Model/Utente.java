package unicam.Model;

public abstract class Utente {
    private int id;
    private String username;
    private String password;
    private String email;
    private boolean occupato;

    public Utente(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.occupato = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOccupato() {
        return occupato;
    }

    public void setOccupato(boolean occupato) {
        this.occupato = occupato;
    }
}
