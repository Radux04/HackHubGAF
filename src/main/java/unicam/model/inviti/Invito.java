package unicam.model.inviti;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import unicam.model.team.Team;
import unicam.model.utenti.user.User;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Team team;
    @ManyToOne
    private User destinatario;
}
