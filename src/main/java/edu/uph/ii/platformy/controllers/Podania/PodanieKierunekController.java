package edu.uph.ii.platformy.controllers.Podania;


import edu.uph.ii.platformy.models.Podania.PodanieKierunki;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.KierunkiRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class PodanieKierunekController {


    @Autowired
    private KierunkiRepository kierunkiRepository;


    @RequestMapping(value="/podanieKierunek.html", method= RequestMethod.GET)
    public String showPodanieKierunek(Model model){
        model.addAttribute("kierunekNowy", new PodanieKierunki());
        return "podanieKierunek";
    }
//    @PostMapping(value = "/podanieKierunek.html")
//    public String showProduktForm(Model model, @Valid @ModelAttribute("kierunekNowy") PodanieKierunki podanieKierunki, BindingResult bindingResult){
//
//        if(bindingResult.hasErrors()){
//            return  "podanieKierunek";
//        }
//        produktRepository.save(produktForm);
//        return "redirect:produktList";
//    }


}

