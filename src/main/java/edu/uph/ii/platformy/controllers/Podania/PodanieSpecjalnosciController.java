package edu.uph.ii.platformy.controllers.Podania;


import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.models.Podania.KierunekPodanie;
import edu.uph.ii.platformy.models.Podania.PodanieSpecjalnosci;
import edu.uph.ii.platformy.models.Role;
import edu.uph.ii.platformy.models.Specjalnosci;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.Podania.PodanieSpecjalnosciRepository;
import edu.uph.ii.platformy.repositories.SpecjalnosciRepository;
import edu.uph.ii.platformy.repositories.UserRepository;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
@SessionAttributes(names={"podanieSpecjalnosci"})
@Log4j2
public class PodanieSpecjalnosciController {



    @Autowired
    private PodanieSpecjalnosciRepository podanieSpecjalnosciRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SpecjalnosciRepository specjalnosciRepository;





    @Secured("ROLE_STUDENT")
    @GetMapping(path="/podanieSpecjalnosciForm.html", params={"id"})
    public String showPodanieSpecjalnosciForm(Model model, @RequestParam("id") Long id){



        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User zalogowany = userRepository.findByUsername(currentPrincipalName);


        model.addAttribute("zalogowany", zalogowany);

        model.addAttribute("zzz",id);

        model.addAttribute("specjalnosci", specjalnosciRepository.findById(id).get());


        model.addAttribute("podanieSpecjalnosci", new PodanieSpecjalnosci());

        return "podanieSpecjalnosciForm";
    }



    @Secured({"ROLE_STUDENT"})
    @RequestMapping(value="/podanieSpecjalnosciForm.html", method= RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String processPodanieSpecjalnosciForm(Model model, @Valid @ModelAttribute("podanieSpecjalnosci") PodanieSpecjalnosci a, BindingResult errors){

        if(errors.hasErrors()){
            return "podanieSpecjalnosciForm";
        }

        a.setCreatedDate(new Date());

        podanieSpecjalnosciRepository.saveAndFlush(a);


        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User zalogowany = userRepository.findByUsername(currentPrincipalName);
        model.addAttribute("zalogowany", zalogowany);

        Specjalnosci specjalnosci = zalogowany.getSpecjalnosci();

        if(specjalnosci.getId()==4){
            model.addAttribute("specjalnosci", specjalnosciRepository.findAll());
            return "redirect:specjalnosciList.html";
        }



        else {
            return "redirect:";
        }

    }






}
