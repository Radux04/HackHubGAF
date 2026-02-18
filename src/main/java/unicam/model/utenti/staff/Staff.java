package unicam.model.utenti.staff;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicam.model.hackathon.entity.Hackathon;
import unicam.model.utenti.Utente;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff extends Utente {
    private RuoliStaff ruolo;
    @ManyToOne
    private Hackathon idHackathon;
}
