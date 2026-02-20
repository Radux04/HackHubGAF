package unicam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Staff extends Utente {
    @Enumerated(EnumType.STRING)
    private RuoliStaff ruolo;
    @ManyToOne
    @JsonBackReference
    private Hackathon hackathon;
}
