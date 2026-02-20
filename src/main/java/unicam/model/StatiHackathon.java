package unicam.model;

import jakarta.persistence.Embeddable;

@Embeddable
public enum StatiHackathon {
    IN_ISCRIZIONE,
    IN_CORSO,
    IN_VALUTAZIONE,
    CONCLUSO
}
