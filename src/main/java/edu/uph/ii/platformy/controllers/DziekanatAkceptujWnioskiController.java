package edu.uph.ii.platformy.controllers;


import com.sun.org.apache.xpath.internal.operations.Mod;
import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.models.Podania.KierunekPodanie;
import edu.uph.ii.platformy.models.Role;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.Podania.KierunekPodanieRepository;
import edu.uph.ii.platformy.repositories.RoleRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Controller
public class DziekanatAkceptujWnioskiController {

    @Autowired
    private KierunekPodanieRepository kierunekPodanieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

//pokazywanie listy
    @RequestMapping(value = "/dziekanatAkceptujWnioski.html", method = {RequestMethod.GET , RequestMethod.POST})
    public String showPodanieRekrutacja(Model model){

        List<KierunekPodanie> kierunekPodanie = kierunekPodanieRepository.findKierunekPodanieByStatus(1);

        model.addAttribute("kierunekpodanie",kierunekPodanie);
        return "dziekanatAkceptujWnioski";
    }

//akceptowanie wniosków
    @RequestMapping(value = "/akceptujRekrutacja.html", method = RequestMethod.GET )
    public String akceptujWniosek(Model model , @RequestParam(name = "id",required = false,defaultValue = "-1") Long id){

        if(id>0) {


            KierunekPodanie kierunekPodanie = kierunekPodanieRepository.findById(id).get();
            Long a = kierunekPodanie.getIdUser();
            if (a > 0) {
                Optional<User> opt = userRepository.findById(a);
                if (opt.isPresent()) {
                    User user = opt.get();
                    Role role = roleRepository.findRoleByType(Role.Types.ROLE_STUDENT);
                    user.setRoles(new HashSet<>(Arrays.asList(role)));


                } else {

                    return "dziekanatAkceptujWnioski";
                }

            }
            model.addAttribute("user", userRepository);
            model.addAttribute("kierunekpodanie", kierunekPodanie);
            kierunekPodanie.setStatus(2);

            kierunekPodanieRepository.deleteById(id);
            return "redirect:dziekanatAkceptujWnioski";

        }else {
            List<KierunekPodanie> kierunekPodanie = kierunekPodanieRepository.findKierunekPodanieByStatus(1);

            model.addAttribute("kierunekpodanie",kierunekPodanie);
            return "redirect:dziekanatAkceptujWnioski";
        }
    }

}
