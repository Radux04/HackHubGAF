package unicam.dto.hackathon;

import java.time.LocalDateTime;

public record PlacementHT (
    LocalDateTime scadenzaIscrizioni,
    LocalDateTime dataInizio,
    LocalDateTime dataFine,
    String luogo) {
}
