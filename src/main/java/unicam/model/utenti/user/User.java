package unicam.model.utenti.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private Ruoli ruolo;
    @OneToOne
    private Team team;
}
