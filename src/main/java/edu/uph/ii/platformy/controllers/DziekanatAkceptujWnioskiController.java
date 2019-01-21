package edu.uph.ii.platformy.controllers;


import com.sun.org.apache.xpath.internal.operations.Mod;
import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.models.Podania.*;
import edu.uph.ii.platformy.repositories.*;
import edu.uph.ii.platformy.repositories.Podania.*;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Controller
public class DziekanatAkceptujWnioskiController {

    @Autowired
    private KierunekPodanieRepository kierunekPodanieRepository;

    @Autowired
    private PodanieSpecjalnosciRepository podanieSpecjalnosciRepository;

    @Autowired
    private SpecjalnosciRepository specjalnosciRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PodanieUbezpieczenieRepository podanieUbezpieczenieRepository;

    @Autowired
    private UbezpieczenieRepository ubezpieczenieRepository;

    @Autowired
    private PodanieUserRepository podanieUserRepository;

    @Autowired
    private KierunkiRepository kierunkiRepository;

    @Autowired
    private StypendiumPodanieRepository stypendiumPodanieRepository;

    @Autowired
    private StypendiaRepository stypendiaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    //pokazywanie listy
    @RequestMapping(value = "/dziekanatAkceptujWnioski.html", method = {RequestMethod.GET , RequestMethod.POST})
    public String showPodanieRekrutacja(Model model){

        List<KierunekPodanie> kierunekPodanie = kierunekPodanieRepository.findKierunekPodanieByStatus(1);
        List<PodanieSpecjalnosci> podanieSpecjalnoscis = podanieSpecjalnosciRepository.findByStatus(1);
        List<PodanieUbezpieczenie> podanieUbezpieczenies = podanieUbezpieczenieRepository.findByStatus(1);
        List<PodanieUser> podanieUser = podanieUserRepository.findByStatus(1);
        List<StypendiumPodanie> stypendiumPodanie = stypendiumPodanieRepository.findStypendiumPodanieByStatus(1);

        model.addAttribute("podanieUser",podanieUser);
        model.addAttribute("podanieUbezpieczenie",podanieUbezpieczenies);
        model.addAttribute("podanieSpecjalnosci",podanieSpecjalnoscis);
        model.addAttribute("kierunekpodanie",kierunekPodanie);
        model.addAttribute("stypendiumPodanie",stypendiumPodanie);
        return "dziekanatAkceptujWnioski";
    }

    //akceptowanie wniosków
    @RequestMapping(value = "/akceptujRekrutacja.html", method = RequestMethod.GET )
    public String akceptujRekrutacja(Model model , @RequestParam(name = "id",required = false,defaultValue = "-1") Long id){

        if(id>0) {

            KierunekPodanie kierunekPodanie = kierunekPodanieRepository.findById(id).get();
            Long b = kierunekPodanie.getIdKierunku(); //pobieranie id z modelu
            Long a = kierunekPodanie.getIdUser();
            if (a > 0) {
                Optional<Kierunki> kier = kierunkiRepository.findById(b); //
                Optional<User> opt = userRepository.findById(a);
                if (opt.isPresent()) {

                    User user = opt.get();
                    Role role = roleRepository.findRoleByType(Role.Types.ROLE_STUDENT);
                    user.setRoles(new HashSet<>(Arrays.asList(role)));

                    Kierunki kierunki = kier.get(); //pobieram kierunek
                    user.setKierunki(kierunki); //i ustawiam nowy
                    userRepository.save(user);

                } else {

                    return "redirect:dziekanatAkceptujWnioski.html";
                }

            }
            model.addAttribute("user", userRepository);
            model.addAttribute("kierunekpodanie", kierunekPodanie);
            kierunekPodanie.setStatus(2);
            kierunekPodanieRepository.delete(kierunekPodanie);


//            kierunekPodanieRepository.deleteById(id);
//            //kierunekPodanieRepository.deleteById(id);
            return "redirect:dziekanatAkceptujWnioski.html";

        }else {
            List<KierunekPodanie> kierunekPodanie = kierunekPodanieRepository.findKierunekPodanieByStatus(1);
            List<PodanieSpecjalnosci> podanieSpecjalnoscis = podanieSpecjalnosciRepository.findByStatus(1);
            List<PodanieUbezpieczenie> podanieUbezpieczenies = podanieUbezpieczenieRepository.findByStatus(1);
            List<PodanieUser> podanieUser = podanieUserRepository.findByStatus(1);
            List<StypendiumPodanie> stypendiumPodanie = stypendiumPodanieRepository.findStypendiumPodanieByStatus(1);

            model.addAttribute("podanieUser",podanieUser);
            model.addAttribute("podanieUbezpieczenie",podanieUbezpieczenies);
            model.addAttribute("podanieSpecjalnosci",podanieSpecjalnoscis);
            model.addAttribute("kierunekpodanie",kierunekPodanie);
            model.addAttribute("stypendiumPodanie",stypendiumPodanie);

            return "redirect:dziekanatAkceptujWnioski.html";
        }
    }

    @RequestMapping(value = "/akceptujStypendia.html", method = RequestMethod.GET )
    public String akceptujStypendia(Model model , @RequestParam(name = "id",required = false,defaultValue = "-1") Long id){

        if(id>0) {


            StypendiumPodanie stypendiumPodanie = stypendiumPodanieRepository.findById(id).get(); //id podania
            Long test2 = stypendiumPodanie.getId(); //pobieranie id z modelu
            Long a = stypendiumPodanie.getIdUser(); //pobieranie uzytkownika z jego id z podania

            if (a > 0) {
                Optional<User> opt = userRepository.findById(a); //znalezienie takiego uzytkownika
                Optional<Stypendia> st = stypendiaRepository.findById(test2); //znalezienie takiego stypendium o id z podania
                if (opt.isPresent() && st.isPresent()) { //sprawdzanie czy istnieja w bazie
                    User user = opt.get(); //pobranie obiektu user
                    Stypendia stypendia = st.get(); //pobieram obiekt stypendia
                    user.setStypendia(stypendia); //ustawiam nowy

                    userRepository.save(user);//zapisuje

                } else {

                    return "dziekanatAkceptujWnioski.html";
                }

            }
            model.addAttribute("user", userRepository);
            stypendiumPodanie.setStatus(2);
            stypendiumPodanieRepository.save(stypendiumPodanie);
            stypendiumPodanieRepository.delete(stypendiumPodanie);
            //podanieSpecjalnosciRepository.deleteById(id);
            return "redirect:dziekanatAkceptujWnioski.html";

        }else {
            List<KierunekPodanie> kierunekPodanie = kierunekPodanieRepository.findKierunekPodanieByStatus(1);
            List<PodanieSpecjalnosci> podanieSpecjalnoscis = podanieSpecjalnosciRepository.findByStatus(1);
            List<PodanieUbezpieczenie> podanieUbezpieczenies = podanieUbezpieczenieRepository.findByStatus(1);
            List<PodanieUser> podanieUser = podanieUserRepository.findByStatus(1);
            List<StypendiumPodanie> stypendiumPodanie = stypendiumPodanieRepository.findStypendiumPodanieByStatus(1);


            model.addAttribute("podanieUser",podanieUser);
            model.addAttribute("podanieUbezpieczenie",podanieUbezpieczenies);
            model.addAttribute("podanieSpecjalnosci",podanieSpecjalnoscis);
            model.addAttribute("kierunekpodanie",kierunekPodanie);
            model.addAttribute("stypendiumPodanie",stypendiumPodanie);

            return "redirect:dziekanatAkceptujWnioski.html";
        }
    }

    @RequestMapping(value = "/odrzucStypendia.html", method = RequestMethod.GET )
    public String odrzucStypendia(Model model , @RequestParam(name = "id",required = false,defaultValue = "-1") Long id){

        StypendiumPodanie stypendiumPodanie = stypendiumPodanieRepository.findById(id).get();
        stypendiumPodanieRepository.delete(stypendiumPodanie);

        return "redirect:dziekanatAkceptujWnioski.html";
    }






    //akceptowanie wniosków
    @RequestMapping(value = "/odrzucRekrutacja.html", method = RequestMethod.GET )
    public String odrzucRekrutacje( @RequestParam(name = "id",required = false,defaultValue = "-1") Long id){

        KierunekPodanie kier = kierunekPodanieRepository.findById(id).get();
        kierunekPodanieRepository.delete(kier);

        return "redirect:dziekanatAkceptujWnioski.html";
    }

    @RequestMapping(value = "/akceptujSpecjalnosci.html", method = RequestMethod.GET )
    public String akceptujSpecjalnosci(Model model , @RequestParam(name = "id",required = false,defaultValue = "-1") Long id){

        if(id>0) {


            PodanieSpecjalnosci podanieSpecjalnosci = podanieSpecjalnosciRepository.findById(id).get(); //id podania
            Long test2 = podanieSpecjalnosci.getIdSpecjalnosci(); //pobieranie id z modelu
            Long a = podanieSpecjalnosci.getIdUsera(); //

            if (a > 0) {
                Optional<User> opt = userRepository.findById(a); //
                Optional<Specjalnosci> spe = specjalnosciRepository.findById(test2); //
                if (opt.isPresent() && spe.isPresent()) { //
                    User user = opt.get(); //


                    Specjalnosci specjalnosci = spe.get(); //
                    user.setSpecjalnosci(specjalnosci); //
                    userRepository.save(user);

                } else {

                    return "dziekanatAkceptujWnioski.html";
                }

            }
            model.addAttribute("user", userRepository);
            podanieSpecjalnosci.setStatus(2);
            podanieSpecjalnosciRepository.save(podanieSpecjalnosci);
            //podanieSpecjalnosciRepository.deleteById(id);
            return "redirect:dziekanatAkceptujWnioski.html";

        }else {
            List<KierunekPodanie> kierunekPodanie = kierunekPodanieRepository.findKierunekPodanieByStatus(1);
            List<PodanieSpecjalnosci> podanieSpecjalnoscis = podanieSpecjalnosciRepository.findByStatus(1);
            List<PodanieUbezpieczenie> podanieUbezpieczenies = podanieUbezpieczenieRepository.findByStatus(1);
            List<PodanieUser> podanieUser = podanieUserRepository.findByStatus(1);
            List<StypendiumPodanie> stypendiumPodanie = stypendiumPodanieRepository.findStypendiumPodanieByStatus(1);

            model.addAttribute("podanieUser",podanieUser);
            model.addAttribute("podanieUbezpieczenie",podanieUbezpieczenies);
            model.addAttribute("podanieSpecjalnosci",podanieSpecjalnoscis);
            model.addAttribute("kierunekpodanie",kierunekPodanie);
            model.addAttribute("stypendiumPodanie",stypendiumPodanie);
            return "redirect:dziekanatAkceptujWnioski.html";
        }
    }





    @RequestMapping(value = "/akceptujUbezpieczenie.html", method = RequestMethod.GET )
    public String akceptujUbezpieczenie(Model model , @RequestParam(name = "id",required = false,defaultValue = "-1") Long id){

        if(id>0) {


            PodanieUbezpieczenie podanieUbezpieczenie = podanieUbezpieczenieRepository.findById(id).get(); //id podania
            Long test2 = podanieUbezpieczenie.getIdUbezpieczenia(); //pobieranie id z modelu
            Long a = podanieUbezpieczenie.getIdUsera(); //

            if (a > 0) {
                Optional<User> opt = userRepository.findById(a); //
                Optional<Ubezpieczenie> ube = ubezpieczenieRepository.findById(test2); //
                if (opt.isPresent() && ube.isPresent()) { //
                    User user = opt.get(); //



                    Ubezpieczenie ubezpieczenie = ube.get(); //
                    System.out.println(user);
                    user.setUbezpieczenie(ubezpieczenie); //
                    System.out.println(user);
                    userRepository.save(user);

                } else {

                    return "dziekanatAkceptujWnioski.html";
                }

            }
            model.addAttribute("user", userRepository);
            podanieUbezpieczenie.setStatus(2);
            podanieUbezpieczenieRepository.save(podanieUbezpieczenie);
            System.out.println(podanieUbezpieczenie);
            //podanieSpecjalnosciRepository.deleteById(id);
            return "redirect:dziekanatAkceptujWnioski.html";

        }else {
            List<KierunekPodanie> kierunekPodanie = kierunekPodanieRepository.findKierunekPodanieByStatus(1);
            List<PodanieSpecjalnosci> podanieSpecjalnoscis = podanieSpecjalnosciRepository.findByStatus(1);
            List<PodanieUbezpieczenie> podanieUbezpieczenies = podanieUbezpieczenieRepository.findByStatus(1);
            List<PodanieUser> podanieUser = podanieUserRepository.findByStatus(1);
            List<StypendiumPodanie> stypendiumPodanie = stypendiumPodanieRepository.findStypendiumPodanieByStatus(1);

            model.addAttribute("podanieUser",podanieUser);
            model.addAttribute("podanieUbezpieczenie",podanieUbezpieczenies);
            model.addAttribute("podanieSpecjalnosci",podanieSpecjalnoscis);
            model.addAttribute("kierunekpodanie",kierunekPodanie);
            model.addAttribute("stypendiumPodanie",stypendiumPodanie);
            return "redirect:dziekanatAkceptujWnioski.html";
        }
    }






    @RequestMapping(value = "/akceptujUser.html", method = RequestMethod.GET )
    public String akceptujUser(Model model, @RequestParam(name = "id",required = false,defaultValue = "-1") Long id){


if(id>=1){

        PodanieUser podanieUser = podanieUserRepository.findById(id).get(); //id podania
              Long a = podanieUser.getIdUsera(); //

        String haslo = podanieUser.getPassword();


        String pass = passwordEncoder.encode(haslo);
        //
//        podanieUser.setPassword(pass);
//        podanieUserRepository.save(podanieUser);
//
                  Optional<User> opt = userRepository.findById(a); //
                    User user = opt.get(); //



            String name = podanieUser.getName();
            String surname = podanieUser.getSurname();
            String email = podanieUser.getEmail();


                    user.setName(name);
                    user.setSurname(surname);
                    user.setEmail(email);
                    user.setPassword(pass);
                    userRepository.save(user);



            podanieUser.setStatus(2);
            podanieUserRepository.save(podanieUser);

            return "redirect:dziekanatAkceptujWnioski.html";
    }

        else {
            List<KierunekPodanie> kierunekPodanie = kierunekPodanieRepository.findKierunekPodanieByStatus(1);
            List<PodanieSpecjalnosci> podanieSpecjalnoscis = podanieSpecjalnosciRepository.findByStatus(1);
            List<PodanieUbezpieczenie> podanieUbezpieczenies = podanieUbezpieczenieRepository.findByStatus(1);
            List<PodanieUser> podanieUser = podanieUserRepository.findByStatus(1);
    List<StypendiumPodanie> stypendiumPodanie = stypendiumPodanieRepository.findStypendiumPodanieByStatus(1);

            model.addAttribute("podanieUser",podanieUser);
            model.addAttribute("podanieUbezpieczenie",podanieUbezpieczenies);
            model.addAttribute("podanieSpecjalnosci",podanieSpecjalnoscis);
            model.addAttribute("kierunekpodanie",kierunekPodanie);
    model.addAttribute("stypendiumPodanie",stypendiumPodanie);
            return "redirect:dziekanatAkceptujWnioski.html";
        }
    }



    @RequestMapping(value = "/odrzucSpecjalnosci.html", method = RequestMethod.GET )
    public String odrzucSpecjalnosci( @RequestParam(name = "id",required = false,defaultValue = "-1") Long id){

        if(id>0) {


            PodanieSpecjalnosci podanieSpecjalnosci = podanieSpecjalnosciRepository.findById(id).get(); //id podania


            podanieSpecjalnosciRepository.delete(podanieSpecjalnosci);

            return "redirect:dziekanatAkceptujWnioski.html";

        }else {

            return "redirect:dziekanatAkceptujWnioski.html";
        }
    }



    @RequestMapping(value = "/odrzucUbezpieczenie.html", method = RequestMethod.GET )
    public String odrzucUbezpieczenie(@RequestParam(name = "id",required = false,defaultValue = "-1") Long id){

        if(id>0) {


            PodanieUbezpieczenie podanieUbezpieczenie = podanieUbezpieczenieRepository.findById(id).get(); //id podania


            podanieUbezpieczenieRepository.delete(podanieUbezpieczenie);

            return "redirect:dziekanatAkceptujWnioski.html";

        }else {

            return "redirect:dziekanatAkceptujWnioski.html";
        }
    }

    @RequestMapping(value = "/odrzucUser.html", method = RequestMethod.GET )
    public String odrzucUser( @RequestParam(name = "id",required = false,defaultValue = "-1") Long id){

        if(id>0) {


            PodanieUser podanieUser = podanieUserRepository.findById(id).get(); //id podania


            podanieUserRepository.delete(podanieUser);
            return "redirect:dziekanatAkceptujWnioski.html";

        }else {

            return "redirect:dziekanatAkceptujWnioski.html";
        }
    }




}