package unicam.model.hackathon.entity;

import jakarta.persistence.*;
import lombok.*;
import unicam.model.utenti.staff.Staff;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "Hackathon")
public class Hackathon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    //private DescrizioneHT descrizione;
    private String regolamento;
    private float premio;
    private int maxSize;
    //private PlacementHT placement;
    private LocalDateTime scadenzaIscrizioni;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String luogo;
    //private StaffHT staff;
    @ManyToOne
    private Staff giudice;
    @ManyToMany
    private List<Staff> mentori;
    @Enumerated(EnumType.STRING)
    private StatiHackathon stato;
    @ManyToOne
    private Staff organizzatore;
    @OneToMany
    private List<Sottomissione> sottomissioni;
}
