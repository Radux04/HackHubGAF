package unicam.model.iscrizione;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicam.model.hackathon.entity.Hackathon;
import unicam.model.team.Team;
import unicam.model.utenti.user.User;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Iscrizione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Hackathon ht;
    @ManyToOne
    private Team team;
}
