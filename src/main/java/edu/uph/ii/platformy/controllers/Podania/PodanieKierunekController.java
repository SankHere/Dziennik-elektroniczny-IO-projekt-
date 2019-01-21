package edu.uph.ii.platformy.controllers.Podania;


import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.repositories.KierunkiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

//Artur
@Controller
public class PodanieKierunekController {

    @Autowired
    private KierunkiRepository kierunkiRepository;

    @RequestMapping(value="/podanieKierunek.html", method= RequestMethod.GET)
    public String showPodanieKierunek(Model model){
        model.addAttribute("kierunekNowy", new Kierunki());
        return "podanieKierunek";
    }
    @PostMapping(value = "/podanieKierunek.html")
    public String showPodanieKierunekForm(Model model, @Valid @ModelAttribute("kierunekNowy") Kierunki podanieKierunki, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "podanieKierunek";
        }

        podanieKierunki.setStatus(1);
        podanieKierunki.setCreatedDate(new Date());

        kierunkiRepository.save(podanieKierunki);

        List<Kierunki> kierunki = kierunkiRepository.findAllKierunkiUsingStatus(2);
        model.addAttribute("kierunki", kierunki);
        return  "kierunkiList";
    }


}

