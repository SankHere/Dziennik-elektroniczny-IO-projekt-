package edu.uph.ii.platformy.controllers.Podania;


import edu.uph.ii.platformy.models.Podania.PodanieSpecjalnosci;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.SpecjalnosciRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import edu.uph.ii.platformy.services.Podania.PodanieSpecjalnosciService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes(names={"podanieSpecjalnosci"})
@Log4j2
public class PodanieSpecjalnosciFormController {

@Autowired
   private PodanieSpecjalnosciService podanieSpecjalnosciService;

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private SpecjalnosciRepository specjalnosciRepository;

    //@Autowired
    public PodanieSpecjalnosciFormController(PodanieSpecjalnosciService podanieSpecjalnosciService)
    {
        this.podanieSpecjalnosciService = podanieSpecjalnosciService;
    }

    @Secured("ROLE_STUDENT")
    @GetMapping(path="/podanieSpecjalnosciForm.html", params={"id"})
    public String showForm(Model model, @RequestParam("id") Long id){



        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User zalogowany = userRepository.findByUsername(currentPrincipalName);


        //User ajdi = userRepository.findBySpecjalnosci(currentPrincipalName);
        //model.addAttribute("specjalnosc",id);
        model.addAttribute("zalogowany", zalogowany);
        //model.addAttribute("ajdi",ajdi);

        model.addAttribute("zzz",id);

        model.addAttribute("specjalnosci", specjalnosciRepository.findById(id).get());


        model.addAttribute("podanieSpecjalnosci", new PodanieSpecjalnosci());

        return "podanieSpecjalnosciForm";
    }



    @Secured({"ROLE_STUDENT"})
    @RequestMapping(value="/podanieSpecjalnosciForm.html", method= RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String processForm(@Valid @ModelAttribute("podanieSpecjalnosci") PodanieSpecjalnosci a, BindingResult errors){

        if(errors.hasErrors()){
            return "podanieSpecjalnosciForm";
        }

        podanieSpecjalnosciService.savePodanieSpecjalnosci(a);

        return "redirect:specjalnosciList.html";//po udanym dodaniu/edycji przekierowujemy na listę
    }

}