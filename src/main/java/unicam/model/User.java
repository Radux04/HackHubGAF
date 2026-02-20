package unicam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
