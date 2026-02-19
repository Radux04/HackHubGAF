package unicam.model.hackathon.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import unicam.model.utenti.staff.Staff;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Hackathon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String nome;
    //private DescrizioneHT descrizione;
    private String regolamento;
    private float premio;
    private int maxSize;
    //private PlacementHT placement;
    private String scadenzaIscrizioni;
    private String dataInizio;
    private String dataFine;
    private String luogo;
    //private StaffHT staff;
    @ManyToOne
    @JsonManagedReference
    private Staff giudice;
    @ManyToMany
    @JsonManagedReference
    private List<Staff> mentori;
    @Enumerated(EnumType.STRING)
    private StatiHackathon stato;
    @ManyToOne
    @JsonManagedReference
    private Staff organizzatore;
    @OneToMany
    private List<Sottomissione> sottomissioni;
}
