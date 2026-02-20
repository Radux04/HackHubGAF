package unicam.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class RichiestaSupporto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Team team;
    private String descrizione;
    @ManyToOne
    private Hackathon hackathon;

    public RichiestaSupporto(Team team, String descrizione, Hackathon hackathon) {
        this.team = team;
        this.descrizione = descrizione;
        this.hackathon = hackathon;
    }
}