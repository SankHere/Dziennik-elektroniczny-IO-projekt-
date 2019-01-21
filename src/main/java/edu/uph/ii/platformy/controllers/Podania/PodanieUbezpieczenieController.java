package edu.uph.ii.platformy.controllers.Podania;


import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.models.Podania.KierunekPodanie;
import edu.uph.ii.platformy.models.Podania.PodanieSpecjalnosci;
import edu.uph.ii.platformy.models.Podania.PodanieUbezpieczenie;
import edu.uph.ii.platformy.repositories.Podania.PodanieSpecjalnosciRepository;
import edu.uph.ii.platformy.repositories.Podania.PodanieUbezpieczenieRepository;
import edu.uph.ii.platformy.repositories.SpecjalnosciRepository;
import edu.uph.ii.platformy.repositories.UbezpieczenieRepository;
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
@SessionAttributes(names={"podanieUbezpieczenie"})
@Log4j2
public class PodanieUbezpieczenieController {


    @Autowired
    private PodanieUbezpieczenieRepository podanieUbezpieczenieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UbezpieczenieRepository ubezpieczenieRepository;




//    @Secured({"ROLE_DZIEKANAT"})
//    @RequestMapping(value="/accessoryList.html", method = {RequestMethod.GET, RequestMethod.POST})
//    public String showPodanieSpecjalnosciList(Model model, BindingResult result, Pageable pageable){
//
//        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//
//        User zalogowany = userRepository.findByUsername(currentPrincipalName);
//
//
//        model.addAttribute("zalogowany", zalogowany);
//        model.addAttribute("adminAkceptujWnioski", podanieSpecjalnosciService.getAllPodanieSpecjalnosci(pageable));
//        return "adminAkceptujWnioski";
//
//    }


    @Secured({"ROLE_STUDENT","ROLE_ADMIN","ROLE_NAUCZYCIEL","ROLE_DZIEKANAT"})
    @GetMapping(path="/podanieUbezpieczenieForm.html", params={"id"})
    public String showPodanieUbezpieczenieForm(Model model, @RequestParam("id") Long id){



        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User zalogowany = userRepository.findByUsername(currentPrincipalName);


        model.addAttribute("zalogowany", zalogowany);

        model.addAttribute("zzz",id);




        model.addAttribute("ubezpieczenie", ubezpieczenieRepository.findById(id).get());


        model.addAttribute("podanieUbezpieczenie", new PodanieUbezpieczenie());

        return "podanieUbezpieczenieForm";
    }



    @Secured({"ROLE_STUDENT","ROLE_ADMIN","ROLE_NAUCZYCIEL","ROLE_DZIEKANAT"})
    @RequestMapping(value="/podanieUbezpieczenieForm.html", method= RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String processPodanieUbezpieczenieForm(Model model, @Valid @ModelAttribute("podanieUbezpieczenie") PodanieUbezpieczenie a, BindingResult errors){

        if(errors.hasErrors()){
            return "podanieUbezpieczenieForm";
        }

        a.setCreatedDate(new Date());

        podanieUbezpieczenieRepository.saveAndFlush(a);


        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User zalogowany = userRepository.findByUsername(currentPrincipalName);
        model.addAttribute("zalogowany", zalogowany);

        Ubezpieczenie ubezpieczenie = zalogowany.getUbezpieczenie();

        if(ubezpieczenie.getId()==4){
            model.addAttribute("ubezpieczenia", ubezpieczenieRepository.findAll());
            return "redirect:ubezpieczeniaList.html";
        }



        else {
            return "redirect:";
        }

    }











}
