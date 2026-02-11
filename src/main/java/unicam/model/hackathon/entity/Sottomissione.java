package unicam.model.hackathon.entity;

import java.io.File;

public class Sottomissione {
    private int id;
    private String titolo;
    private String descrizione;
    private File file;

    public Sottomissione(String titolo, String descrizione, File file) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.file = file;
    }
}
