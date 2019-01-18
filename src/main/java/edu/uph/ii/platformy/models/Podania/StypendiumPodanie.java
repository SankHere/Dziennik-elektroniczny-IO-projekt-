package edu.uph.ii.platformy.models.Podania;


import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.models.Stypendia;
import edu.uph.ii.platformy.models.User;
import lombok.Data;
import lombok.Getter;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;

@Entity
@Table(name = "stypendiumpodanie")
@Data
public class StypendiumPodanie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    private String opis;

    @Column(name="created_date")
    private Date createdDate;

    private Float avg;


//    @Valid
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_kierunku", nullable = false)
//    private Kierunki kierunki;
//
//    @Valid
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_stypendium", nullable = false)
//    private Stypendia stypendia;
//
//    @Valid
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_user", nullable = false)
//    private User user;

    private Long idUser;
    private Long idStypendium;
    private String nazwaStypendium;

    private int status;

    public StypendiumPodanie(Long id, String name, String surname, String opis, Date createdDate, Float avg, String nazwaStypendium,Long idUser,Long idStypendium,int status) {
        this(name,surname,opis,createdDate,avg,nazwaStypendium,idUser,idStypendium,status);
        this.id = id;
    }

    public StypendiumPodanie(String name,String surname,String opis,Date createdDate,Float avg,String nazwaStypendium,Long idUser,Long idStypendium,int status) {
        this.name = name;
        this.surname=surname;
        this.idStypendium=idStypendium;
        this.status=status;
        this.nazwaStypendium=nazwaStypendium;
        this.idUser=idUser;
        this.opis=opis;
        this.createdDate=createdDate;
        this.avg=avg;
    }

    public StypendiumPodanie() {

    }
}
