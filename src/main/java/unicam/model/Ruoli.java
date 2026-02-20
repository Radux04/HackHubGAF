package unicam.model;

import jakarta.persistence.Embeddable;

@Embeddable
public enum Ruoli {
    UTENTE,
    MEMBROTEAM,
    COORDINATORE
}
