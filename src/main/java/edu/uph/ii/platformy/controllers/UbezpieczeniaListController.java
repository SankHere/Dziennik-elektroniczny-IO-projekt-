package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.models.Specjalnosci;
import edu.uph.ii.platformy.models.Ubezpieczenie;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.StypendiaRepository;
import edu.uph.ii.platformy.repositories.UbezpieczenieRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@Log4j2
public class UbezpieczeniaListController {

    @Autowired
    private UbezpieczenieRepository ubezpieczenieRepository;


    @Autowired
    private UserRepository userRepository;

    @Secured({"ROLE_STUDENT","ROLE_ADMIN","ROLE_NAUCZYCIEL","ROLE_DZIEKANAT"})
    @RequestMapping(value = "/ubezpieczeniaList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showUbezpieczeniaList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User zalogowany = userRepository.findByUsername(currentPrincipalName);
        model.addAttribute("zalogowany", zalogowany);


        Ubezpieczenie ubezpieczenie = zalogowany.getUbezpieczenie();

        model.addAttribute("twoje", ubezpieczenie.getName());
        model.addAttribute("cena",ubezpieczenie.getPrice());

        if (ubezpieczenie.getId() == 4) {


            Ubezpieczenie ubezpieczeni = zalogowany.getUbezpieczenie();
            //String u = ubezpieczenie.getName();

            //model.addAttribute("twoje", u);
            model.addAttribute("ubezpieczenia", ubezpieczenieRepository.findAll());
            model.addAttribute("test", 2);



            return "ubezpieczeniaList";
        } else {
            model.addAttribute("test", 1);

//            model.addAttribute("twoje", ubezpieczenie.getName());
            model.addAttribute("cena",ubezpieczenie.getPrice());


            model.addAttribute("ubezpieczenia", ubezpieczenieRepository.findAll());
            return "ubezpieczeniaList";
        }


    }

}