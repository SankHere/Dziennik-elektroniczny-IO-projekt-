package edu.uph.ii.platformy.models;



import edu.uph.ii.platformy.validators.annotations.InvalidValues;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Table(name = "przedmiot")
@Getter @Setter
@NoArgsConstructor
public class Przedmiot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 30)
    private String name;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kierunku", nullable = false)
    private Kierunki kierunki;

    public Przedmiot(long id, String name, Kierunki kierunki) {
        this(name, kierunki);
        this.id = id;
    }

    public Przedmiot(String name, Kierunki kierunki) {
        this.name = name;
        this.kierunki = kierunki;
    }
}
