package unicam.model.consegna;

import java.io.File;

public class Consegna {

    private int id;
    private String titolo;
    private String descrizione;
    private File file;
    public int idIscrizione;


    public Consegna(int id, String titolo, String descrizione, File file, int idIscrizione) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.file = file;
        this.idIscrizione = idIscrizione;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getIdIscrizione() {
        return idIscrizione;
    }

    public void setIdIscrizione(int idIscrizione) {
        this.idIscrizione = idIscrizione;
    }
}
