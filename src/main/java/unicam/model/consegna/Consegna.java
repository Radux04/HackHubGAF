package unicam.model.consegna;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unicam.model.hackathon.entity.Sottomissione;
import unicam.model.iscrizione.Iscrizione;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    }
}
