package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.KierunkiRepository;
import edu.uph.ii.platformy.repositories.SpecjalnosciRepository;
import edu.uph.ii.platformy.repositories.StypendiaRepository;
import edu.uph.ii.platformy.repositories.UbezpieczenieRepository;
import edu.uph.ii.platformy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by grzesiek on 23.08.2017.
 */
@Controller
public class UserRegistrationFormController {
    @Autowired
    private UserService userService;

    @Autowired
    private KierunkiRepository kierunkiRepository;

    @Autowired
    private SpecjalnosciRepository specjalnosciRepository;

    @Autowired
    private UbezpieczenieRepository ubezpieczenieRepository;

    @Autowired
    private StypendiaRepository stypendiaRepository;

    @GetMapping("/registrationForm.html")
    public String registration(Model model) {
        model.addAttribute("userCommand", new User());
        return "registrationForm";
    }

    @PostMapping("/registrationForm.html")
    public String registrations(@Valid @ModelAttribute("userCommand") User userForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registrationForm";
        }
        userForm.setDataRejestracji(new Date());
        //userForm.setKierunki();


        userForm.setKierunki(kierunkiRepository.findByName("Test"));
        userForm.setSpecjalnosci(specjalnosciRepository.findByName("Test"));
        userForm.setUbezpieczenie(ubezpieczenieRepository.findByName("Test"));
        userForm.setStypendia(stypendiaRepository.findByName("Test"));
        userService.save(userForm);
        return "registrationSuccess";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //aby użytkownik nie mógł sobie wstrzyknąć aktywacji konta oraz ról (np., ADMIN)
        //roles są na wszelki wypadek, bo warstwa serwisów i tak ustawia ROLE_USER dla nowego usera
        binder.setDisallowedFields("enabled", "roles");
        //binder.setDisallowedFields("dataRejestracji");
    }

}