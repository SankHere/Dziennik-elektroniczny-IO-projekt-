package edu.uph.ii.platformy.models;

import edu.uph.ii.platformy.validators.annotations.InvalidValues;
import javafx.css.StyleableObjectProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "stypendia")
@Data
@NoArgsConstructor
public class Stypendia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 30)
    private String name;

    @Positive
    @Max(1000000)
    private Double kwota;

    public Stypendia(Long id, String name, Double kwota) {
        this(name, kwota);
        this.id = id;
    }


    public Stypendia(String name, Double kwota) {
        this.name = name;
        this.kwota = kwota;
    }


}