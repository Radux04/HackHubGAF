package unicam.model.supporto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicam.model.hackathon.entity.Hackathon;
import unicam.model.team.Team;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RichiestaSupporto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Team team;
    private String descrizione;
    @ManyToOne
    private Hackathon hackathon;
}