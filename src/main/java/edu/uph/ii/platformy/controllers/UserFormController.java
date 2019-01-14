package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes(names={"trainingRoomType", "accessoryList", "trainingRoom"})
@Log4j2
public class UserFormController {

    private UserService userService;

    //Wstrzyknięcie zależności przez konstruktor. Od wersji 4.3 Springa nie trzeba używać adnontacji @Autowired, gdy mamy jeden konstruktor
    // @Autowired
    public UserFormController(UserService userService)
    {
        this.userService = userService;
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/userForm.html", method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){

        model.addAttribute("user",
                id.isPresent()?
                        userService.getUser(id.get()):
                        new User());

        return "userForm";
    }
//
    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/userForm.html", method= RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String processForm(@Valid @ModelAttribute("user") User user, BindingResult errors){

        if(errors.hasErrors()){
            return "user";
        }

        //log.info("Data utworzenia komponentu "+v.getCreatedDate());
        //log.info("Data edycji komponentu "+new Date());

        userService.save(user);

        return "redirect:userList.html";//po udanym dodaniu/edycji przekierowujemy na listę
    }


}
