package unicam.model.hackathon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicam.model.team.Team;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Segnalazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Team team;
    private String descrizione;
}
