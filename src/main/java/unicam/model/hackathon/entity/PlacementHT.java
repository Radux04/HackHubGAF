package unicam.model.hackathon.entity;

import java.time.LocalDateTime;

public class PlacementHT {
    private LocalDateTime scadenzaIscrizioni;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String luogo;

    public PlacementHT(LocalDateTime scadenzaIscrizioni, LocalDateTime dataInizio, LocalDateTime dataFine, String luogo) {
        this.scadenzaIscrizioni = scadenzaIscrizioni;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.luogo = luogo;
    }

    public LocalDateTime getScadenzaIscrizioni() {
        return scadenzaIscrizioni;
    }

    public void setScadenzaIscrizioni(LocalDateTime scadenzaIscrizioni) {
        this.scadenzaIscrizioni = scadenzaIscrizioni;
    }

    public LocalDateTime getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDateTime getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDateTime dataFine) {
        this.dataFine = dataFine;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }
}
