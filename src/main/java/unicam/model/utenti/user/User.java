package unicam.model.utenti.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicam.model.team.Team;
import unicam.model.utenti.Utente;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User extends Utente {
    @Enumerated(EnumType.STRING)
    private Ruoli ruolo;
    @OneToOne
    private Team team;
}
