package unicam.model.consegna;

import unicam.model.hackathon.entity.Sottomissione;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Consegna {

    private int id;
    private String descrizione;
    private Sottomissione sottomissione;
    public int idIscrizione;
    private int voto;


    public Consegna(int idIscrizione, String  descrizione, Sottomissione sottomissione) {
        this.descrizione = descrizione;
        this.idIscrizione = idIscrizione;
        this.sottomissione = sottomissione;
        voto = -1;

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

    public Sottomissione getSottomissione() {
        return sottomissione;
    }

    public void setSottomissione(Sottomissione sottomissione) {
        this.sottomissione = sottomissione;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }
}
