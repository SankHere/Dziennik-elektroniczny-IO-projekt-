package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.repositories.UserRepository;
import edu.uph.ii.platformy.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserListController {

    //private UserService userService;
    private UserRepository userRepository;


//    @GetMapping(value="/errors")
//    public String resetUserList(){
//        return "redirect:userList.html";
//    }


    @RequestMapping(value="/userList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showUserList(Model model){
        model.addAttribute("user", userRepository.findAll());
        return  "userList";
    }



}
