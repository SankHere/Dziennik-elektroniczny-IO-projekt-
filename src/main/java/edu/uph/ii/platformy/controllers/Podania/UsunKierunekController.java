package edu.uph.ii.platformy.controllers.Podania;


import edu.uph.ii.platformy.exceptions.KierunkiNotFoundException;
import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.repositories.KierunkiRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
@Log4j2
public class UsunKierunekController {

    @Autowired
    private KierunkiRepository kierunkiRepository;

    @RequestMapping(value="/usunKierunek.html",method = RequestMethod.GET)
    public String deleteKierunek1(Model model, @RequestParam("id") Optional<Long> id) {

        //Kierunki kierunek = kierunkiRepository.findById(id.get());
        Optional<Kierunki> optionalKierunki = kierunkiRepository.findById(id.get());
        Kierunki kierunek = optionalKierunki.orElseThrow(()->new KierunkiNotFoundException(id.get()));


        model.addAttribute("kierunek", kierunek);
        return "usunKierunek";
    }

    @PostMapping(value = "/usunKierunek.html")
    public String deleteKierunek2(Model model, @Valid @ModelAttribute("kierunek") Kierunki kierunek, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "usunKierunek";
        }

        kierunkiRepository.saveAndFlush(kierunek);

        List<Kierunki> kierunki = kierunkiRepository.findAllKierunkiUsingStatus(2);

        model.addAttribute("kierunki", kierunki );
        return "kierunkiList";

    }

}




