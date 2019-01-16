package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.repositories.KierunkiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//Artur

@Controller
public class AdminAkceptujWnioskiController {
    @Autowired
    private KierunkiRepository kierunkiRepository;


    @RequestMapping(value="/adminAkceptujWnioski.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showWnioskiList(Model model){

        List<Kierunki> kierunki = kierunkiRepository.findAllKierunkiUsingStatus(1);

        model.addAttribute("kierunki", kierunki );
        return  "adminAkceptujWnioski";
    }


    @RequestMapping(value="/akceptuj.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String akceptujWniosek(Model model, @RequestParam(name = "id", required = false, defaultValue = "-1") Long id){

//        if(id > 0){
//            Kierunki kier = kierunkiRepository.findById(id).get();
//        }else{
//
//        }

        Kierunki kier = kierunkiRepository.findById(id).get();

        kier.setStatus(2);
        kierunkiRepository.save(kier);

        List<Kierunki> kierunki = kierunkiRepository.findAllKierunkiUsingStatus(1);
        model.addAttribute("kierunki", kierunki);
        return  "adminAkceptujWnioski";
    }
}

