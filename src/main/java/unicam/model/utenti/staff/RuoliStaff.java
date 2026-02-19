package unicam.model.utenti.staff;

import jakarta.persistence.Embeddable;

@Embeddable
public enum RuoliStaff {
    ORGANIZZATORE,
    GIUDICE,
    MENTORE
}
