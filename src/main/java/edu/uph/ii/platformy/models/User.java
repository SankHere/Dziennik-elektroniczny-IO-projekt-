package edu.uph.ii.platformy.models;

import edu.uph.ii.platformy.validators.annotations.InvalidValues;
import edu.uph.ii.platformy.validators.annotations.UniqueUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

/**
 * Created by grzesiek on 23.08.2017.
 */
@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 36)
    @UniqueUsername
    private String username;
    private String password;
    @Transient//pole nie będzie odwzorowane w db
    private String passwordConfirm;
    private boolean enabled = false;

    @AssertTrue
    private boolean isPasswordsEquals(){
        return password == null || passwordConfirm == null || password.equals(passwordConfirm);
    }

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

    @Column(name="data_rejestracji", nullable = false)
    private Date dataRejestracji;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
/*
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "oceny",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "egzamin_id"))
    private Set<Egzamin> egzamins;
*/
    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kierunku", nullable = false)
    private Kierunki kierunki;
//    private Set<Kierunki> kierunki;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_specjalnosci", nullable = false)
  //  private Set<Specjalnosci> specjalnosci;
    private Specjalnosci specjalnosci;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_stypendium", nullable = false)
    //private Set<Stypendia> stypendia;
    private Stypendia stypendia;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ubezbieczenia", nullable = false)
    //private Set<Ubezpieczenie> ubezpieczenie;
    private Ubezpieczenie ubezpieczenie;

    public User(String username, String name, String surname, String email, Date dataRejestracji, Kierunki kierunki, Specjalnosci specjalnosci, Stypendia stypendia, Ubezpieczenie ubezpieczenie){
        this(username, false, name, surname, email, dataRejestracji, kierunki, specjalnosci, stypendia, ubezpieczenie);
    }

    public User(String username, boolean enabled, String name, String surname, String email, Date dataRejestracji, Kierunki kierunki, Specjalnosci specjalnosci, Stypendia stypendia, Ubezpieczenie ubezpieczenie){
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dataRejestracji = dataRejestracji;
        this.kierunki = kierunki;
        this.specjalnosci = specjalnosci;
        this.stypendia = stypendia;
        this.ubezpieczenie = ubezpieczenie;
        this.enabled = enabled;
    }


}