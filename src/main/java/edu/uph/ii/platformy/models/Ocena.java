package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;



@Entity
@Table(name = "ocena")
@Getter @Setter
public class Ocena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Positive
    private double ocena;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_egzaminu", nullable = false)
    private Egzamin egzamin;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usera", nullable = false)
    private User user;

    public Ocena(long id, Double ocena, Egzamin egzamin, User user) {
        this(ocena, egzamin, user);
        this.id = id;
    }

    public Ocena(Double ocena, Egzamin egzamin, User user) {
        this.ocena = ocena;
        this.egzamin = egzamin;
        this.user = user;
    }
}
