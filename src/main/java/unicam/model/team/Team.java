package unicam.model.team;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicam.model.utenti.user.User;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descrizione;
    @OneToOne
    private User coordinatore;
    private boolean occupato;
    @OneToMany
    private List<User> membri;
}
