package edu.uph.ii.platformy.controllers.Podania;


import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.models.Podania.KierunekPodanie;
import edu.uph.ii.platformy.models.Podania.PodanieSpecjalnosci;
import edu.uph.ii.platformy.models.Podania.PodanieUbezpieczenie;
import edu.uph.ii.platformy.models.Podania.PodanieUser;
import edu.uph.ii.platformy.repositories.Podania.PodanieSpecjalnosciRepository;
import edu.uph.ii.platformy.repositories.Podania.PodanieUbezpieczenieRepository;
import edu.uph.ii.platformy.repositories.Podania.PodanieUserRepository;
import edu.uph.ii.platformy.repositories.SpecjalnosciRepository;
import edu.uph.ii.platformy.repositories.UbezpieczenieRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
@SessionAttributes(names={"podanieUser"})
@Log4j2
public class PodanieUserController {


    @Autowired
    private PodanieUserRepository podanieUserRepository;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;







    @Secured({"ROLE_STUDENT","ROLE_ADMIN","ROLE_NAUCZYCIEL","ROLE_DZIEKANAT"})
    @GetMapping(path="/podanieUserForm.html")
    public String showPodanieUserForm(Model model){



        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User zalogowany = userRepository.findByUsername(currentPrincipalName);

        Long id = zalogowany.getId();

        model.addAttribute("zalogowany", zalogowany);


        model.addAttribute("user", userRepository.findById(id).get());






        model.addAttribute("podanieUser", new PodanieUser());


        return "podanieUserForm";
    }



    @Secured({"ROLE_STUDENT","ROLE_ADMIN","ROLE_NAUCZYCIEL","ROLE_DZIEKANAT"})
    @RequestMapping(value="/podanieUserForm.html", method= RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String processPodanieUserForm(Model model, @Valid @ModelAttribute("podanieUser") PodanieUser a, BindingResult errors){

        if(errors.hasErrors()){
            return "podanieUserForm";
        }






        a.setCreatedDate(new Date());

//        String haslo = a.getPassword();
//        a.setPassword(new String(passwordEncoder.encode(haslo)));



        podanieUserRepository.save(a);




        //

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User zalogowany = userRepository.findByUsername(currentPrincipalName);
        model.addAttribute("zalogowany", zalogowany);

            model.addAttribute("user", userRepository.findAll());
            return "redirect:";



    }











}