package unicam.model.consegna;

import unicam.model.hackathon.entity.Sottomissione;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Consegna {

    private int id;
    private String descrizione;
    private Map<Sottomissione, File> risposte;
    public int idIscrizione;


    public Consegna(int idIscrizione) {
        this.descrizione = descrizione;
        this.risposte = new HashMap<>();
        this.idIscrizione = idIscrizione;
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

    public Map<Sottomissione, File> getRisposte() {
        return risposte;
    }

    public void setRisposte(Map<Sottomissione, File> risposte) {
        this.risposte = risposte;
    }
}
