package edu.uph.ii.platformy.config;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UbezpieczenieRepository ubezpieczenieRepository;

    @Autowired
    private SpecjalnosciRepository specjalnosciRepository;

    @Autowired
    private KierunkiRepository kierunkiRepository;

    @Autowired
    private StypendiaRepository stypendiaRepository;

    @Autowired
    private PrzedmiotRepository przedmiotRepository;

    @Autowired
    private EgzaminRepository egzaminRepository;

    @Autowired
    private OcenaRepository ocenaRepository;


    @Bean
    InitializingBean init() {

        return () -> {



            if(roleRepository.findAll().isEmpty()){
                try {

                    //tworzymy kierunki
                    Kierunki ki1 = new Kierunki("Informatyka", 100, new Date(01-10-2011));
                    kierunkiRepository.save(ki1);
                    Kierunki ki2 = new Kierunki("Matematyka", 80, new Date( 13-07-2000));
                    kierunkiRepository.save(ki2);
                    Kierunki ki3 = new Kierunki("Chemia", 50, new Date(11-11-2014));
                    kierunkiRepository.save(ki3);
                    //nauczyciel nie powinien miec zadnego przedmiotu to bedzie tutaj nalezec
                    Kierunki ki4 = new Kierunki("BRAK", 1, new Date(01-01-2000));
                    kierunkiRepository.save(ki4);


                    //tworzymy przedmioty
                    Przedmiot pr1 = new Przedmiot("Podstawy technologi WWW");
                    pr1.setKierunki(ki1);
                    przedmiotRepository.save(pr1);
                    Przedmiot pr2 = new Przedmiot("Programowanie obiektowe");
                    pr2.setKierunki(ki1);
                    przedmiotRepository.save(pr2);
                    Przedmiot pr3 = new Przedmiot("Grafika komuterowa");
                    pr3.setKierunki(ki1);
                    przedmiotRepository.save(pr3);
                    Przedmiot pr4 = new Przedmiot("Rozkład atomów");
                    pr4.setKierunki(ki3);
                    przedmiotRepository.save(pr4);
                    Przedmiot pr5 = new Przedmiot("Całki w świecie");
                    pr5.setKierunki(ki2);
                    przedmiotRepository.save(pr5);
                    //uczen nie powinien miec zadnego przedmiotu to bedzie tutaj nalezec
                    Przedmiot pr6 = new Przedmiot("BRAK");
                    pr6.setKierunki(ki4);
                    przedmiotRepository.save(pr6);

                    //tworzenie egzaminów
                    Egzamin eg1 = new Egzamin("Zaprojektuj dom",  new Date(14-01-2019));
                    eg1.setPrzedmiot(pr3);
                    egzaminRepository.save(eg1);
                    Egzamin eg2 = new Egzamin("Projekt firma", new Date(22-01-2019));
                    eg2.setPrzedmiot(pr1);
                    egzaminRepository.save(eg2);
                    Egzamin eg3 = new Egzamin("Strona domowa", new Date(11-01-2019));
                    eg3.setPrzedmiot(pr1);
                    egzaminRepository.save(eg3);

                    //tworzenie specjalnosci
                    Specjalnosci si1 = new Specjalnosci("C++");
                    si1.setKierunki(ki1);
                    specjalnosciRepository.save(si1);
                    Specjalnosci si2 = new Specjalnosci("HTML");
                    si2.setKierunki(ki1);
                    specjalnosciRepository.save(si2);
                    Specjalnosci si3 = new Specjalnosci("Matematyka dyskretna");
                    si3.setKierunki(ki2);
                    specjalnosciRepository.save(si3);

                    Specjalnosci si4 = new Specjalnosci("Brak");
                    si4.setKierunki(ki4);
                    specjalnosciRepository.save(si4);

                    //tworzenie stypenidów
                    Stypendia st1 = new Stypendia("male", 500.54);
                    stypendiaRepository.save(st1);
                    Stypendia st2 = new Stypendia("srednie", 951.65);
                    stypendiaRepository.save(st2);
                    Stypendia st3 = new Stypendia("duze", 1300.45);
                    stypendiaRepository.save(st3);

                    Stypendia st4 = new Stypendia("BRAK", 5.0);
                    stypendiaRepository.save(st4);

                    //tworzenie ubezpieczenie
                    Ubezpieczenie ub1 = new Ubezpieczenie("Warta", 157.53);
                    ubezpieczenieRepository.save(ub1);
                    Ubezpieczenie ub2 = new Ubezpieczenie("Axa", 222.17);
                    ubezpieczenieRepository.save(ub2);
                    Ubezpieczenie ub3 = new Ubezpieczenie("Generali", 173.22);
                    ubezpieczenieRepository.save(ub3);

                    Ubezpieczenie ub4 = new Ubezpieczenie("Brak", 5.0);
                    ubezpieczenieRepository.save(ub4);

                    //tworzenie uzytkowników
                    Role roleUser = roleRepository.save(new Role(Role.Types.ROLE_USER));
                    Role roleStudent = roleRepository.save(new Role(Role.Types.ROLE_STUDENT));
                    Role roleDziekanat = roleRepository.save(new Role(Role.Types.ROLE_DZIEKANAT));
                    Role roleRada = roleRepository.save(new Role(Role.Types.ROLE_RADA));
                    Role roleNauczyciel = roleRepository.save(new Role(Role.Types.ROLE_NAUCZYCIEL));
                    Role roleAdmin = roleRepository.save(new Role(Role.Types.ROLE_ADMIN));

                    User user = new User("user", true, "imieUser", "nazUser", "emailUser", new Date(06-02-1999),ki4, si4, st4, ub4);
                    user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                    user.setPassword(passwordEncoder.encode("user"));

                    User student = new User("student", true, "imieStudent", "nazStudent", "emailStudent", new Date(11-06-1997),ki1, si2, st3, ub2);
                    student.setRoles(new HashSet<>(Arrays.asList(roleStudent)));
                    student.setPassword(passwordEncoder.encode("student"));

                    User dziekanat = new User("dziekanat", true, "imieDziekanat", "nazDziekanat", "emailDziekanat", new Date(22-12-1983),ki4, si4, st4, ub2);
                    dziekanat.setRoles(new HashSet<>(Arrays.asList(roleDziekanat)));
                    dziekanat.setPassword(passwordEncoder.encode("dziekanat"));

                    User nauczyciel = new User("nauczyciel1", true, "imieNauczyciel1", "nazNauczyciel1", "emailNauczyciel1", new Date(17-11-1977),ki4, si1, st4, ub1);
                    nauczyciel.setRoles(new HashSet<>(Arrays.asList(roleNauczyciel)));
                    nauczyciel.setPassword(passwordEncoder.encode("nauczyciel1"));

                    User nauczycie2 = new User("nauczyciel2", true, "imieNauczyciel2", "nazNauczyciel2", "emailNauczyciel2", new Date(17-11-1977),ki4, si2, st4, ub4);
                    nauczycie2.setRoles(new HashSet<>(Arrays.asList(roleNauczyciel, roleRada)));
                    nauczycie2.setPassword(passwordEncoder.encode("nauczyciel2"));


                    User admin = new User("admin", true, "imieAdmin", "nazAdmin", "emailAdmin", new Date(11-05-2001),ki4, si4, st4, ub4);
                    admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                    admin.setPassword(passwordEncoder.encode("admin"));

                    userRepository.save(user);
                    userRepository.save(admin);
                    userRepository.save(student);
                    userRepository.save(dziekanat);
                    userRepository.save(nauczyciel);
                    userRepository.save(nauczycie2);


                    //Tworzenie oceny
                    Ocena oc1 = new Ocena(5.0, eg1, student);
                    ocenaRepository.save(oc1);
                    Ocena oc2 = new Ocena(3.5, eg2, student);
                    ocenaRepository.save(oc2);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        };
    }

}
