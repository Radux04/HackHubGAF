package unicam.model.utenti.user;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicam.model.team.Team;
import unicam.model.utenti.Utente;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Utente {
    private Ruoli ruolo;
    @OneToOne
    private Team teameam;
}
