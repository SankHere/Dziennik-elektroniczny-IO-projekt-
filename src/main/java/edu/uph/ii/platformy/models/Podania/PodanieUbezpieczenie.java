package edu.uph.ii.platformy.models.Podania;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class PodanieUbezpieczenie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    @Column(name="created_date")
    private Date createdDate;

    private String nazwaUbezpieczenia;
    private Double price;

    private Long idUbezpieczenia;
    private Long idUsera;

    private int status;


    public PodanieUbezpieczenie(Long id, String name, String surname, Date createdDate, String nazwaUbezpieczenia, Double price, Long idUbezpieczenia, Long idUsera, int status){
        this(name, surname, createdDate, nazwaUbezpieczenia, price, idUbezpieczenia,idUsera,status);
        this.id=id;
    }

    public PodanieUbezpieczenie( String name, String surname, Date createdDate, String nazwaUbezpieczenia, Double price, Long idUbezpieczenia, Long idUsera, int status){
        this.name=name;
        this.surname=surname;
        this.createdDate=createdDate;
        this.nazwaUbezpieczenia=nazwaUbezpieczenia;
        this.idUbezpieczenia=idUbezpieczenia;
        this.idUsera=idUsera;
        this.status=status;
    }

}
