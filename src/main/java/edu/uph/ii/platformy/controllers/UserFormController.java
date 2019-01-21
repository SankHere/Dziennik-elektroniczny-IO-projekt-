package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.models.Egzamin;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.UserRepository;
import edu.uph.ii.platformy.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes(names={"egzamin", "user"})
@Log4j2
public class UserFormController {

    private UserService userService;
    private UserRepository userRepository;
    //Wstrzyknięcie zależności przez konstruktor. Od wersji 4.3 Springa nie trzeba używać adnontacji @Autowired, gdy mamy jeden konstruktor
    // @Autowired
    public UserFormController(UserService userService)
    {
        this.userService = userService;
    }


    @Secured("ROLE_DZIEKANAT")
    @RequestMapping(value="/userForm.html",params = "did",method= RequestMethod.GET)
    public String showUserForm(Model model, Long id){

        if(id != null) {
            model.addAttribute("user", userRepository.getOne(id));

        }
        return "userForm";
    }
//
    @Secured("ROLE_DZIEKANAT")
    @RequestMapping(value="/userForm.html", method= RequestMethod.POST)

    //@ResponseStatus(HttpStatus.CREATED)
    public String processUserForm(@Valid @ModelAttribute("user") User user, BindingResult errors){

        if(errors.hasErrors()){
            return "userForm";
        }

        //log.info("Data utworzenia komponentu "+v.getCreatedDate());
        //log.info("Data edycji komponentu "+new Date());

        userService.save(user);

        return "redirect:userList.html";//po udanym dodaniu/edycji przekierowujemy na listę
    }

}
