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
public class Sottomissione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titolo;
    private String descrizione;

    public Sottomissione(String titolo, String descrizione) {
        this.titolo = titolo;
        this.descrizione = descrizione;
    }
}
