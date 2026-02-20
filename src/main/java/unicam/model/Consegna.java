package unicam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Consegna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descrizione;
    @ManyToOne
    private Sottomissione sottomissione;
    @ManyToOne
    private Iscrizione iscrizione;
    private int voto;

    public Consegna(String descrizione, Sottomissione sottomissione, Iscrizione iscrizione) {
        this.descrizione = descrizione;
        this.sottomissione = sottomissione;
        this.iscrizione = iscrizione;
        this.voto = 0;
    }
}
