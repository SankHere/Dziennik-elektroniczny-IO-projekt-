package edu.uph.ii.platformy.models.Podania;


import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.models.Przedmiot;
import edu.uph.ii.platformy.models.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.Date;

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

    @Column(name="created_date")
    private Date createdDate;

    private String schoolName;
    private int status;


    private String nazwaKierunku;
    private Long idKierunku;
    private Long idUser;

//    @Valid
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_usera", nullable = false)
//    private User user;


//    public KierunekPodanie(Long id,String name,String surname, int avg, String schoolName, int status,int idKierunku,String nazwaKierunku,Date createdDate){
//        this.name=name;
//        this.schoolName=schoolName;
//        this.surname=surname;
//        this.avg=avg;
//        this.status=status;
//        this.idKierunku=idKierunku;
//        this.nazwaKierunku=nazwaKierunku;
//        this.id=id;
//    }


    public KierunekPodanie() {

    }

    public KierunekPodanie(Long id, String name, String surname, int avg ,String schoolName, int status , Date createdDate, Long idKierunku, String nazwaKierunku,Long idUser) {
        this(name,surname,avg,schoolName,status,createdDate,idKierunku,nazwaKierunku,idUser);
        this.id = id;
    }

    public KierunekPodanie(String name,String surname, int avg,String schoolName, int status, Date createdDate, Long idKierunku, String nazwaKierunku,Long idUser) {
        this.name = name;
        this.schoolName=schoolName;
        this.avg=avg;
        this.idKierunku=idKierunku;
        this.nazwaKierunku=nazwaKierunku;
        this.createdDate = createdDate;
        this.surname=surname;
        this.status = status;
        this.idUser=idUser;
    }
}
