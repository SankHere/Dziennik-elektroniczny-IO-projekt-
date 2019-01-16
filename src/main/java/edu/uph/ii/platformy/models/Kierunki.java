package edu.uph.ii.platformy.models;

import edu.uph.ii.platformy.validators.annotations.InvalidValues;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Table(name = "kierunki")
@Data
@NoArgsConstructor
public class Kierunki {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 30)
    private String name;

    @Positive
    private int liczbaMiejsc;

    @Length(min = 2, max = 1000)
    private String opis;

    @Column(name="created_date")
    private Date createdDate;

    private int status;

    public Kierunki(long id, String name, int liczbaMiejsc, Date createdDate, String opis, int status) {
        this(name, liczbaMiejsc, createdDate, opis, status);
        this.id = id;
    }

    public Kierunki(String name, int liczbaMiejsc, Date createdDate, String opis, int status) {
        this.name = name;
        this.liczbaMiejsc = liczbaMiejsc;
        this.createdDate = createdDate;
        this.opis = opis;
        this.status = status;
    }
}