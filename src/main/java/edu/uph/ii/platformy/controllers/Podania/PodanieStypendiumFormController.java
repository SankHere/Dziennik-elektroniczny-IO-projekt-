package edu.uph.ii.platformy.controllers.Podania;


import edu.uph.ii.platformy.models.Podania.KierunekPodanie;
import edu.uph.ii.platformy.models.Podania.StypendiumPodanie;
import edu.uph.ii.platformy.models.Stypendia;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.Podania.StypendiumPodanieRepository;
import edu.uph.ii.platformy.repositories.StypendiaRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import edu.uph.ii.platformy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;

@Controller
public class PodanieStypendiumFormController {

    @Autowired
    private StypendiumPodanieRepository stypendiumPodanieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StypendiaRepository stypendiaRepository;

    @Secured("ROLE_STUDENT")
    @RequestMapping(value = "/stypendiaForm.html" , method = RequestMethod.GET)
    public String showForm(Model model , @RequestParam("id") Long id){


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleName = authentication.getName();


        //pobranie aktualnie zalogowanego uzytkownika
        User zalogowany = userRepository.findByUsername(currentPrincipleName);
        //wyszukannie stypendium o danym id i pobranie go
        model.addAttribute("stypendium",stypendiaRepository.findById(id).get());
        model.addAttribute("zalogowany",zalogowany);
        model.addAttribute("zzz", id);
//        model.addAttribute("stypendium", stypendiaRepository.findById(id));

        if(id != null) {
            model.addAttribute(new StypendiumPodanie());

        }

        return "stypendiaForm";
    }
}
