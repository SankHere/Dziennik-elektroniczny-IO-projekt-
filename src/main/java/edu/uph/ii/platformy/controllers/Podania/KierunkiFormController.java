package edu.uph.ii.platformy.controllers.Podania;


import edu.uph.ii.platformy.models.Podania.KierunekPodanie;
import edu.uph.ii.platformy.models.Podania.PodanieSpecjalnosci;
import edu.uph.ii.platformy.models.Specjalnosci;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.KierunkiRepository;
import edu.uph.ii.platformy.repositories.Podania.KierunekPodanieRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@Controller
@Getter
@Setter
public class KierunkiFormController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KierunekPodanieRepository kierunekPodanieRepository;

    @Autowired
    private KierunkiRepository kierunkiRepository;


    @Secured("ROLE_USER")
    @RequestMapping(value="/kierunkiForm.html",method = RequestMethod.GET)
    public String showKierunkiForm(Model model, @RequestParam("id") Long id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleName = authentication.getName();

        User zalogowany = userRepository.findByUsername(currentPrincipleName);

        model.addAttribute("kierunek",kierunkiRepository.findById(id).get());

        model.addAttribute("zalogowany",zalogowany);

        model.addAttribute("zzz", id);


        if(id != null) {
            model.addAttribute(new KierunekPodanie());

        }
        return "kierunkiForm";
    }

    @Secured("ROLE_USER")
    @RequestMapping(value="/kierunkiForm.html", method= RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String processKierunkiForm(@Valid @ModelAttribute("kierunekPodanie") KierunekPodanie kierunekPodanie){
        //saveKierunekPodanie(a);

        kierunekPodanie.setStatus(1);
        kierunekPodanie.setCreatedDate(new Date());
        kierunekPodanieRepository.saveAndFlush(kierunekPodanie);


        return "redirect:kierunkiList.html";//po udanym dodaniu/edycji przekierowujemy na listę
    }
    public void saveKierunekPodanie(KierunekPodanie kierunekPodanie){
        kierunekPodanieRepository.save(kierunekPodanie);
    }

}
