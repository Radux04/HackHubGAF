package unicam.model.utenti.staff;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import unicam.model.hackathon.entity.Hackathon;
import unicam.model.utenti.Utente;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Staff extends Utente {
    @Enumerated(EnumType.STRING)
    private RuoliStaff ruolo;
    @ManyToOne
    @JsonBackReference
    private Hackathon hackathon;
}
