package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.exceptions.KierunkiNotFoundException;
import edu.uph.ii.platformy.exceptions.StypendiaNotFoundException;
import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

//Artur
@Controller
public class AdminAkceptujWnioskiController {
    @Autowired
    private KierunkiRepository kierunkiRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrzedmiotRepository przedmiotRepository;
    @Autowired
    private OcenaRepository ocenaRepository;
    @Autowired
    private SpecjalnosciRepository specjalnosciRepository;
    @Autowired
    private StypendiaRepository stypendiaRepository;

    @RequestMapping(value="/adminAkceptujWnioski.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showAdminAkceptujWnioski(Model model){
//      Do akceptacji kierunków
        List<Kierunki> kierunki = kierunkiRepository.findAllKierunkiUsingStatus(1);
        model.addAttribute("kierunki", kierunki );

//        Do usuniecia kierunku
        List<Kierunki> deletekierunki = kierunkiRepository.findAllKierunkiUsingStatus(3);
        model.addAttribute("deleteKierunki", deletekierunki);

        return  "adminAkceptujWnioski";
    }


    @RequestMapping(value="/akceptujKierunek.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String akceptujKierunek(@RequestParam(name = "id", required = false, defaultValue = "-1") Long id){

        Kierunki kier = kierunkiRepository.findById(id).get();

        kier.setStatus(2);
        kierunkiRepository.save(kier);
        return  "redirect:adminAkceptujWnioski.html";
    }

    @RequestMapping(value="/odrzucKierunek.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String odrzucKierunek(@RequestParam(name = "id", required = false, defaultValue = "-1") Long id){

    Kierunki kier = kierunkiRepository.findById(id).get();

        kierunkiRepository.delete(kier);
        return  "redirect:adminAkceptujWnioski.html";
}



    @RequestMapping(value="/deleteKierunek.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteKierunke(@RequestParam(name = "id", required = false, defaultValue = "-1") Long id){
        long help = (long) 4;
        Optional<Kierunki> opt = kierunkiRepository.findById(help);
        if(opt.isPresent()){
            Kierunki pusty = opt.get();

            Kierunki kier = kierunkiRepository.findById(id).get();
            List<User> user = userRepository.findByKierunki(kier);

            List<Przedmiot> prze = przedmiotRepository.findPrzedmiotByKierunki(kier);
            List<Specjalnosci> spec = specjalnosciRepository.findSpecjalnosciByKierunki(kier);

            for(User u: user){
                u.setKierunki(pusty);
                Role role = roleRepository.findRoleByType(Role.Types.ROLE_USER);
                u.setRoles(new HashSet<>(Arrays.asList(role)));
            }
            for(Przedmiot p: prze){
                p.setKierunki(pusty);
            }
            for(Specjalnosci s: spec){
                s.setKierunki(pusty);
            }

            kierunkiRepository.delete(kier);
        }

        return  "redirect:adminAkceptujWnioski.html";
    }
    @RequestMapping(value="/nieUsuwajKierunek.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String nieUsuwajKierunek(@RequestParam(name = "id", required = false, defaultValue = "-1") Long id){

        Kierunki kier = kierunkiRepository.findById(id).get();
        kier.setStatus(2);
        kierunkiRepository.save(kier);
        return  "redirect:adminAkceptujWnioski.html";
    }

    @RequestMapping(value="/usunStudenta.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String usunStudenta( @RequestParam(name = "id", required = false, defaultValue = "-1") Long id){


        User user = userRepository.findById(id).get();
        List<Ocena> ocena = ocenaRepository.findByUser(user);

        for(Ocena o: ocena){
            ocenaRepository.delete(o);
        }
        userRepository.delete(user);

        return  "redirect:userList.html";
    }

    @RequestMapping(value="/usunStypendia.html",method = RequestMethod.GET)
    public String showForm(Model model, @RequestParam("id") Long id) {

        Stypendia stypendia = stypendiaRepository.findById(id).get();

        List<User> user = userRepository.findByStypendia(stypendia);

//        for(User u: user){
//        userRepository.findByStypendia(stypendia.setId(4));
//        }
//        stypendiaRepository.deleteById(id);


        model.addAttribute("stypendia", stypendia);
        return "stypendiaList";
    }


}

