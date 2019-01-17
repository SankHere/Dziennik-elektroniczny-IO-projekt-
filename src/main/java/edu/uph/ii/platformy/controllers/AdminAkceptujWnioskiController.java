package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.KierunkiRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

//Artur

@Controller
public class AdminAkceptujWnioskiController {
    @Autowired
    private KierunkiRepository kierunkiRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/adminAkceptujWnioski.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showWnioskiList(Model model){
//      Do akceptacji kierunków
        List<Kierunki> kierunki = kierunkiRepository.findAllKierunkiUsingStatus(1);
        model.addAttribute("kierunki", kierunki );

//        Do usuniecia kierunku
        List<Kierunki> deletekierunki = kierunkiRepository.findAllKierunkiUsingStatus(3);
        model.addAttribute("deleteKierunki", deletekierunki);

        return  "adminAkceptujWnioski";
    }


    @RequestMapping(value="/akceptujKierunek.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String akceptujKierunek(Model model, @RequestParam(name = "id", required = false, defaultValue = "-1") Long id){


        Kierunki kier = kierunkiRepository.findById(id).get();

        kier.setStatus(2);
        kierunkiRepository.save(kier);
//        // Do dodania kierunku
//        List<Kierunki> kierunki = kierunkiRepository.findAllKierunkiUsingStatus(1);
//        model.addAttribute("kierunki", kierunki);
//        //  Do usuniecia kierunku
//        List<Kierunki> deletekierunki = kierunkiRepository.findAllKierunkiUsingStatus(3);
//        model.addAttribute("deleteKierunki", deletekierunki);
        return  "redirect:adminAkceptujWnioski.html";
    }



    @RequestMapping(value="/deleteKierunek.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteKierunke(Model model, @RequestParam(name = "id", required = false, defaultValue = "-1") Long id){
        long help = (long) 4;
        Optional<Kierunki> opt = kierunkiRepository.findById(help);
        if(opt.isPresent()){
            Kierunki pusty = opt.get();

            Kierunki kier = kierunkiRepository.findById(id).get();

            List<User> user = userRepository.findByKierunki(kier);

            for(User u: user){
                u.setKierunki(pusty);

            }
            kierunkiRepository.delete(kier);
        }

//        // Do dodania kierunku
//        List<Kierunki> kierunki = kierunkiRepository.findAllKierunkiUsingStatus(1);
//        model.addAttribute("kierunki", kierunki);
//        //  Do usuniecia kierunku
//        List<Kierunki> deletekierunki = kierunkiRepository.findAllKierunkiUsingStatus(3);
//        model.addAttribute("deleteKierunki", deletekierunki);
        return  "redirect:adminAkceptujWnioski.html";
    }
}

