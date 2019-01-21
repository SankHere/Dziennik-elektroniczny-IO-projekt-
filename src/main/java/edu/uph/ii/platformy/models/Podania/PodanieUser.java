package edu.uph.ii.platformy.models.Podania;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class PodanieUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 30)
    //@InvalidValues(ignoreCase = true, values = {"Artur"})
    private String name;


    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 30)
    private String surname;

    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 30)
    private String email;

    private String password;

    @Column(name="created_date")
    private Date createdDate;

    private Long idUsera;

    private int status;


    public PodanieUser(Long id, String name, String surname, String email, String password, Date createdDate, Long idUsera, int status){
        this(name, surname, email, password, createdDate, idUsera, status);
        this.id=id;
    }

    public PodanieUser(String name, String surname, String email, String password, Date createdDate, Long idUsera, int status){
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.password=password;
        this.createdDate=createdDate;
        this.idUsera=idUsera;
        this.status=status;
    }

}
