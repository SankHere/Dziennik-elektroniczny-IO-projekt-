package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@Table(name = "podanie")
@NoArgsConstructor
public class Podanie{

    @Min(0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //zrób name w select na stronie np. rekrutacja na kierunki, utworzenie nowego przedmiotu.
    private int name;


    private String opis;


    // null oczekujacy, pokazuje pracownikowi na liście z przyciskiem akceptuj zmieniajacy stan na 1
    // 1 - zaakcceptowany przez pracownika, zmienia stan na 2
    // 2 - zaakceptowany przez admina, pokazuje się lista adminowi i on zmienia stan na 3
    //3 - pokazuje na liście pracownika wraz z przyciskiem do zrobienia który przekierowuje na formularz, później zmiana na 4
    //4 zaakceptowany
    private int stan;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usera", nullable = false)
    private User user;

    public Podanie(int name, String opis, int stan){
        this.name = name;
        this.opis=opis;
        this.stan=stan;
    }
    public Podanie(int id, String opis, int stan, User user) {
        this(opis, stan, user);
        this.id = id;
    }

    public Podanie(String opis, int stan, User user) {
        this.opis = opis;
        this.stan = stan;
        this.user = user;
    }

}