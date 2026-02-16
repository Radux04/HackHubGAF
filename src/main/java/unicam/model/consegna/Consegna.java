package unicam.model.consegna;

public class Consegna {

    private int id;
    private String descrizione;
    private int idSottomissione;
    private int idIscrizione;
    private int voto;


    public Consegna(int idIscrizione, String  descrizione, int sottomissione) {
        this.descrizione = descrizione;
        this.idIscrizione = idIscrizione;
        this.idSottomissione = sottomissione;
        voto = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getIdIscrizione() {
        return idIscrizione;
    }

    public void setIdIscrizione(int idIscrizione) {
        this.idIscrizione = idIscrizione;
    }

    public int getSottomissione() {
        return idSottomissione;
    }

    public void setSottomissione(int sottomissione) {
        this.idSottomissione = sottomissione;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }
}
