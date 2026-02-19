package unicam.model.utenti.user;

import jakarta.persistence.Embeddable;

@Embeddable
public enum Ruoli {
    UTENTE,
    MEMBROTEAM,
    COORDINATORE
}
