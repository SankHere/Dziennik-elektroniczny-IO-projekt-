package edu.uph.ii.platformy.models.Podania;


import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.models.Przedmiot;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "kierunekpodanie")
@Data
public class KierunekPodanie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String surname;

    @Max(5)
    private int avg;


    private String schoolName;
    private int status;


    private String nazwaKierunku;
    private int idKierunku;


    public KierunekPodanie(Long id,String name,String surname, int avg, String schoolName, int status,int idKierunku,String nazwaKierunku){
        this.name=name;
        this.schoolName=schoolName;
        this.surname=surname;
        this.avg=avg;
        this.status=status;
        this.idKierunku=idKierunku;
        this.nazwaKierunku=nazwaKierunku;
        this.id=id;
    }


    public KierunekPodanie() {

    }
}
