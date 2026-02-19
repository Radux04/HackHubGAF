package unicam.dto.consegna;


public record ConsegnaRequest (
     Long idSottomissione,
     String descrizione,
     Long idIscrizione) {}



