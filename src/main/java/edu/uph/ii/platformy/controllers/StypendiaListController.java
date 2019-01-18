package edu.uph.ii.platformy.controllers;


import com.sun.org.apache.xpath.internal.operations.Mod;
import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.models.Podania.KierunekPodanie;
import edu.uph.ii.platformy.models.Podania.StypendiumPodanie;
import edu.uph.ii.platformy.models.Stypendia;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.KierunkiRepository;
import edu.uph.ii.platformy.repositories.Podania.StypendiumPodanieRepository;
import edu.uph.ii.platformy.repositories.StypendiaRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;


@Controller
@Log4j2
public class StypendiaListController {

    @Autowired
    private StypendiaRepository stypendiaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KierunkiRepository kierunkiRepository;

    @Autowired
    private StypendiumPodanieRepository stypendiumPodanieRepository;

    //pobrac id zalogowanego studenta i sprawdzic czy w id_stypendium jest 4 jesli nie to formularz i lista , jesli tak to wypisac jego stypednium
    @RequestMapping(value="/stypendiaList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showStypendiaList(Model model) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleName = authentication.getName();

        User zalogowany = userRepository.findByUsername(currentPrincipleName);


        if (zalogowany.getStypendia().getId() == 4) {
            model.addAttribute("zalogowany", zalogowany);

            model.addAttribute("stypendia", stypendiaRepository.findAll());
            return "stypendiaList";
        } else {

            model.addAttribute("stypendium", zalogowany.getStypendia());
            return "stypendiaList";
        }
    }

}




