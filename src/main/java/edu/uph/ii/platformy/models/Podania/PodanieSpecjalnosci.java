package edu.uph.ii.platformy.models.Podania;

import edu.uph.ii.platformy.models.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name = "podaniespecjalnosci")
@NoArgsConstructor
public class PodanieSpecjalnosci {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String nazwaSpecjalnosci;

    //private String opis;

    private int idSpecjalnosci;
    private int idUsera;


    public PodanieSpecjalnosci(Long id, String name, String surname, String nazwaSpecjalnosci, int idSpecjalnosci, int idUsera){
        this(name, surname, nazwaSpecjalnosci, idSpecjalnosci,idUsera);
        this.id=id;
    }

    public PodanieSpecjalnosci(String name, String surname, String nazwaSpecjalnosci, int idSpecjalnosci, int idUsera){
        this.name=name;
        this.surname=surname;
        this.nazwaSpecjalnosci=nazwaSpecjalnosci;
        this.idSpecjalnosci=idSpecjalnosci;
        this.idUsera=idUsera;
    }


}
