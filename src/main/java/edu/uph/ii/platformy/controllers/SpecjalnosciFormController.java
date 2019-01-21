package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.models.Specjalnosci;
import edu.uph.ii.platformy.services.SpecjalnosciService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes(names={"specjalnosci"})
@Log4j2
public class SpecjalnosciFormController {

    private SpecjalnosciService specjalnosciService;

    //@Autowired
    public SpecjalnosciFormController(SpecjalnosciService specjalnosciService)
    {
        this.specjalnosciService = specjalnosciService;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/specjalnosciForm.html", method= RequestMethod.GET)
    public String showSpecjalnosciForm(Model model, Optional<Long> id){

        model.addAttribute("specjalnosci",
                id.isPresent()?
                        specjalnosciService.getSpecjalnosci(id.get()):
                        new Specjalnosci());

        return "specjalnosciForm";
    }



    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/specjalnosciForm.html", method= RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String processSpecjalnosciForm(@Valid @ModelAttribute("specjalnosci") Specjalnosci a, BindingResult errors){

        if(errors.hasErrors()){
            return "specjalnosciForm";
        }

        //log.info("Data utworzenia komponentu "+v.getCreatedDate());
        //log.info("Data edycji komponentu "+new Date());

        specjalnosciService.saveSpecjalnosci(a);

        return "redirect:specjalnosciList.html";//po udanym dodaniu/edycji przekierowujemy na listę
    }

}