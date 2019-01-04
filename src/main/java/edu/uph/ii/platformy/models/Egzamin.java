package edu.uph.ii.platformy.models;

import edu.uph.ii.platformy.validators.annotations.InvalidValues;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Table(name = "egzamin")
@Getter @Setter
public class Egzamin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 30)
    @InvalidValues(ignoreCase = true, values = {"Napisz kod","Rózniczkowanie", "Probówka i ja"})
    private String name;

    @Column(name="data_egzaminu", nullable = false)
    private Date dataEgzaminu;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_przedmiotu", nullable = false)
    private Przedmiot przedmiot;

    public Egzamin(long id, String name, Date dataEgzaminu) {
        this(name, dataEgzaminu);
        this.id = id;
    }

    public Egzamin(String name, Date dataEgzaminu) {
        this.name = name;
        this.dataEgzaminu = dataEgzaminu;
    }
}
