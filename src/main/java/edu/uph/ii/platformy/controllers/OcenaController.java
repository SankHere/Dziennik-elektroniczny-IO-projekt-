package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@Log4j2
public class OcenaController {

    @Autowired
    private OcenaRepository ocenaRepository;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private KierunkiRepository kierunkiRepository;

    @Autowired
    private PrzedmiotRepository przedmiotRepository;

    @Autowired
    private EgzaminRepository egzaminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value="/wstawianieOceny.html", method= RequestMethod.GET)
    public String showWstawianieOceny(Model model, @RequestParam(name = "id", required = false, defaultValue = "-1") Long id){

        if(id > 0){
            User user = userRepository.findById(id).get();

            Kierunki kierunki = user.getKierunki();

            List<Przedmiot> przedmiot = przedmiotRepository.findPrzedmiotByKierunki(kierunki);

            List<Egzamin> egzamin = egzaminRepository.findByListPrzedmiotu(przedmiot);

            model.addAttribute("user", user);
            model.addAttribute("egzamin", egzamin);
            model.addAttribute("oceny", new Ocena());

            return "wstawianieOceny";

        }else{
            Role r = roleRepository.findRoleByType(Role.Types.ROLE_STUDENT);

            List<User> user = userRepository.findByRoles(r);

            model.addAttribute("user", user);
            return  "userList";
        }

    }

    @PostMapping(value = "/wstawianieOceny.html")
    public String zapiszOcene(Model model, @Valid @ModelAttribute("oceny") Ocena oceny, BindingResult bindingResult){

        ocenaRepository.save(oceny);

        Role r = roleRepository.findRoleByType(Role.Types.ROLE_STUDENT);

        List<User> user = userRepository.findByRoles(r);

        model.addAttribute("user", user);
        return  "userList";
    }
}




