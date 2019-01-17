package edu.uph.ii.platformy.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
@Table(name = "ocena")
@Data
@NoArgsConstructor
public class Ocena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Positive
    @Max(5)
    private double ocena;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_egzaminu", nullable = false)
    private Egzamin egzamin;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usera", nullable = false)
    private User user;

    public Ocena(long id, User user, Egzamin egzamin, Double ocena) {
        this(user, egzamin, ocena);
        this.id = id;
    }

    public Ocena(User user, Egzamin egzamin, Double ocena) {
        this.ocena = ocena;
        this.egzamin = egzamin;
        this.user = user;
    }
}
