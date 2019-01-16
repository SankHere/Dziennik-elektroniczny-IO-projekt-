package edu.uph.ii.platformy.controllers;



import edu.uph.ii.platformy.models.Role;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.RoleRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserListController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value="/userList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showUserList(Model model){

        Role r = roleRepository.findRoleByType(Role.Types.ROLE_STUDENT);
//
        List<User> user = userRepository.findByRoles(r);

        model.addAttribute("user", user);
        return  "userList";
    }



}
