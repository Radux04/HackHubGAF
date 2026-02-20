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
public class Segnalazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Team team;
    @ManyToOne
    private Hackathon hackathon;
    @ManyToOne
    private Staff mentore;
    private String descrizione;


    public Segnalazione(Team team,  Hackathon hackathon, Staff mentore, String descrizione) {
        this.team = team;
        this.hackathon = hackathon;
        this.mentore = mentore;
        this.descrizione = descrizione;
    }
}
