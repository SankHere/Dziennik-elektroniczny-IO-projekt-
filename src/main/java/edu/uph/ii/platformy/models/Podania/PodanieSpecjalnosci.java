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
@Getter
@Setter
@NoArgsConstructor
public class PodanieSpecjalnosci {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String opis;

    @Valid
    @ManyToOne
    @JoinColumn(name="id_specjalnosci", nullable = false)
    private Specjalnosci specjalnosci;



    public PodanieSpecjalnosci(Long id, String opis, Specjalnosci specjalnosci){
        this(opis, specjalnosci);
        this.id=id;
    }

    public PodanieSpecjalnosci(String opis, Specjalnosci specjalnosci){

        this.opis=opis;
        this.specjalnosci=specjalnosci;



    }


}
