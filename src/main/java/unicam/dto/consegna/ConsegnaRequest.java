package unicam.dto.consegna;

import lombok.Data;

@Data
public class ConsegnaRequest {
    private Long idSottomissione;
    private String descrizione;
    private Long idIscrizione;
}



