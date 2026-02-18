package unicam.dto.hackathon;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlacementHT {
    private LocalDateTime scadenzaIscrizioni;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String luogo;
}
