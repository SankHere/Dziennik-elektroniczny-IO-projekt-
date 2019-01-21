package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.controllers.commands.SpecjalnoscFilter;
import edu.uph.ii.platformy.models.Specjalnosci;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.Podania.PodanieSpecjalnosciRepository;
import edu.uph.ii.platformy.repositories.SpecjalnosciRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import edu.uph.ii.platformy.services.SpecjalnosciService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DecimalFormat;

@Controller
public class SpecjalnosciListController {

    @Autowired
    private SpecjalnosciRepository specjalnosciRepository;

//    @Autowired
//    private SpecjalnosciService specjalnosciService;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value="/specjalnosciList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showSpecjalnosciList(Model model){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User zalogowany = userRepository.findByUsername(currentPrincipalName);
        model.addAttribute("zalogowany", zalogowany);

        Specjalnosci specjalnosci = zalogowany.getSpecjalnosci();

        if(specjalnosci.getId()==4){


            model.addAttribute("specjalnosci", specjalnosciRepository.findAll());

            Specjalnosci specjalnosc = zalogowany.getSpecjalnosci();
            String specja = specjalnosc.getName();

            model.addAttribute("twoje", specja);
            model.addAttribute("specjalnosci", specjalnosciRepository.findAll());
            model.addAttribute("test", 2);
            return "specjalnosciList";
        }



        else {

            Specjalnosci specjalnosc = zalogowany.getSpecjalnosci();
            String specja = specjalnosc.getName();

            model.addAttribute("twoje", specja);
            model.addAttribute("specjalnosci", specjalnosciRepository.findAll());
            model.addAttribute("test", 1);
            return "specjalnosciList";
        }

    }
}