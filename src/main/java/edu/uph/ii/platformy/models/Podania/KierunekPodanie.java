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
@Table(name = "kierunekPodanie")
@Data
@Getter
@Setter
public class KierunekPodanie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    private String surname;

    @Max(6)
    private Float avg;

    @NotBlank
    private String schoolName;
    private int status;

    @NotBlank
    private String nazwaKierunku;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kierunku", nullable = false)
    private Kierunki kierunki;


    public KierunekPodanie(String name,String surname, Float avg, String schoolName, int status,Kierunki kierunki,String nazwaKierunku){
        this.name=name;
        this.schoolName=schoolName;
        this.surname=surname;
        this.avg=avg;
        this.status=status;
        this.kierunki=kierunki;
        this.nazwaKierunku=nazwaKierunku;
    }


    public KierunekPodanie() {

    }
}
