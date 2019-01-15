package edu.uph.ii.platformy.controllers.Podania;


import edu.uph.ii.platformy.models.Podania.PodanieKierunki;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.KierunkiRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class PodanieKierunekController {


    @Autowired
    private KierunkiRepository kierunkiRepository;

    @RequestMapping(value="/podanieKierunek.html", method= RequestMethod.GET)
    public String showPodanieKierunek(Model model){
        model.addAttribute("kierunekNowy", new PodanieKierunki());
        return "podanieKierunek";
    }

}

