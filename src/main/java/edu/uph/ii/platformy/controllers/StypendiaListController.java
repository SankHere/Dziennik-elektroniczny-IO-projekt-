package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.StypendiaRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@Log4j2
public class StypendiaListController {

    @Autowired
    private StypendiaRepository stypendiaRepository;

    @Autowired
    private UserRepository userRepository;

    //pobrac id zalogowanego studenta i sprawdzic czy w id_stypendium jest 4 jesli nie to formularz i lista , jesli tak to wypisac jego stypednium
    @RequestMapping(value="/stypendiaList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showStypendiaList(Model model) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleName = authentication.getName();

        User zalogowany = userRepository.findByUsername(currentPrincipleName);

        if (zalogowany.getId() != 4) {
            model.addAttribute("zalogowany", zalogowany);

            model.addAttribute("stypendia", stypendiaRepository.findAll());
            return "stypendiaList";
        } else {

            model.addAttribute("stypendium", zalogowany.getStypendia());
            return "stypendiaList";
        }
    }



}




