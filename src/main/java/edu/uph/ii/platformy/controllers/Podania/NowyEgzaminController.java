package edu.uph.ii.platformy.controllers.Podania;

import edu.uph.ii.platformy.models.Egzamin;
import edu.uph.ii.platformy.repositories.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@Log4j2
public class NowyEgzaminController {

    @Autowired
    private PrzedmiotRepository przedmiotRepository;
    @Autowired
    private EgzaminRepository egzaminRepository;

    @RequestMapping(value="/nowyEgzamin.html", method = RequestMethod.GET)
    public String showNowyEgzamin(Model model){

        model.addAttribute("nowyEgzamin", new Egzamin());
        model.addAttribute("przedmioty", przedmiotRepository.findAll());

        return  "nowyEgzamin";
    }

    @PostMapping(value = "/nowyEgzamin.html")
    public String zapiszNowyEgzamin(Model model, @Valid @ModelAttribute("nowyEgzamin") Egzamin egzamin, BindingResult bindingResult){

        egzaminRepository.save(egzamin);

        return  "redirect:";
    }
}




