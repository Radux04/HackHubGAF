package unicam.model.utenti.staff;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicam.model.hackathon.entity.Hackathon;
import unicam.model.utenti.Utente;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Staff extends Utente {
    @Enumerated(EnumType.STRING)
    private RuoliStaff ruolo;
    @ManyToOne
    private Hackathon hackathon;
}
