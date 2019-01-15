package edu.uph.ii.platformy.controllers.Podania;


import edu.uph.ii.platformy.models.Podania.KierunekPodanie;
import edu.uph.ii.platformy.models.Specjalnosci;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.KierunkiRepository;
import edu.uph.ii.platformy.repositories.Podania.KierunekPodanieRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@Getter
@Setter
public class KierunkiFormController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KierunekPodanieRepository kierunekPodanieRepository;

    @Autowired
    private KierunkiRepository kierunkiRepository;


    @Secured("ROLE_USER")
    @RequestMapping(value="/kierunkiForm.html",method = RequestMethod.GET)
    public String showForm(Model model, @RequestParam("id") Long id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleName = authentication.getName();

        User zalogowany = userRepository.findByUsername(currentPrincipleName);

        model.addAttribute("kierunek",kierunkiRepository.findById(id).get());

        model.addAttribute("zalogowany",zalogowany);

        if(id != null) {
            model.addAttribute(new KierunekPodanie());

        }
        return "kierunkiForm";
    }

}
