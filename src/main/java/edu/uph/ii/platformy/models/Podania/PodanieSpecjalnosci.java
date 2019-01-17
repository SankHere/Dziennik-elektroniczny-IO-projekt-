package edu.uph.ii.platformy.models.Podania;

import edu.uph.ii.platformy.models.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class PodanieSpecjalnosci {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    @Column(name="created_date")
    private Date createdDate;

    private String nazwaSpecjalnosci;

    private Long idSpecjalnosci;
    private Long idUsera;

    private int status;


    public PodanieSpecjalnosci(Long id, String name, String surname, Date createdDate, String nazwaSpecjalnosci, Long idSpecjalnosci, Long idUsera, int status){
        this(name, surname, createdDate, nazwaSpecjalnosci, idSpecjalnosci,idUsera,status);
        this.id=id;
    }

    public PodanieSpecjalnosci(String name, String surname, Date createdDate, String nazwaSpecjalnosci, Long idSpecjalnosci, Long idUsera, int status){
        this.name=name;
        this.surname=surname;
        this.createdDate=createdDate;
        this.nazwaSpecjalnosci=nazwaSpecjalnosci;
        this.idSpecjalnosci=idSpecjalnosci;
        this.idUsera=idUsera;
        this.status=status;
    }


}