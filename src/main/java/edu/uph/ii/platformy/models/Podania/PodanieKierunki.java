package edu.uph.ii.platformy.models.Podania;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Table(name = "podaniekierunki")
@Data
@NoArgsConstructor
public class PodanieKierunki {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 30)
    private String name;

    @Positive
    private int liczbaMiejsc;

    @Column(name="created_date")
    private Date createdDate;

    @Positive
    private int status;

    @Length(min = 2, max = 1000)
    private String opis;

    public PodanieKierunki(long id, String name, int liczbaMiejsc, Date createdDate, String opis, int status) {
        this(name, liczbaMiejsc, createdDate, opis, status);
        this.id = id;
    }

    public PodanieKierunki(String name, int liczbaMiejsc, Date createdDate, String opis, int status) {
        this.name = name;
        this.liczbaMiejsc = liczbaMiejsc;
        this.createdDate = createdDate;
        this.opis = opis;
        this.status = status;

    }
}
