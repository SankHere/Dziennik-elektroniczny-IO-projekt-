package edu.uph.ii.platformy.controllers.Podania;


import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.models.Ocena;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.KierunkiRepository;
import edu.uph.ii.platformy.repositories.OcenaRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@Log4j2
public class OcenyListController {

    @Autowired
    private OcenaRepository ocenaRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/mojeOceny.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showOcenyList(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User zalogowany = userRepository.findByUsername(currentPrincipalName);

        List<Ocena> oceny = ocenaRepository.findByUser(zalogowany);


        model.addAttribute("mojeOceny", oceny );
        return  "mojeOceny";
    }
}




