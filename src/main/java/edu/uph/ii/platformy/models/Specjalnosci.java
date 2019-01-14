package edu.uph.ii.platformy.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
@Table(name = "specjalnosci")
@Data
@NoArgsConstructor
public class Specjalnosci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 30)
    private String name;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kierunku")
    private Kierunki kierunki;

    public Specjalnosci(Long id, String name ) {
        this(name);
        this.id = id;
    }

    public Specjalnosci(String name) {
        this.name = name;
    }
}