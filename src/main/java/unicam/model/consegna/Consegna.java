package unicam.model.consegna;

public class Consegna {

    private Long id;
    private String descrizione;
    private Long idSottomissione;
    private Long idIscrizione;
    private int voto;


    public Consegna(Long idIscrizione, String  descrizione, Long sottomissione) {
        this.descrizione = descrizione;
        this.idIscrizione = idIscrizione;
        this.idSottomissione = sottomissione;
        voto = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Long getIdIscrizione() {
        return idIscrizione;
    }

    public void setIdIscrizione(Long idIscrizione) {
        this.idIscrizione = idIscrizione;
    }

    public Long getSottomissione() {
        return idSottomissione;
    }

    public void setSottomissione(Long sottomissione) {
        this.idSottomissione = sottomissione;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }
}
