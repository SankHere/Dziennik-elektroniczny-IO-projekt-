package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Podanie;
import edu.uph.ii.platformy.services.PodanieService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes(names={"podanie"})
@Log4j2
public class PodanieFormController {

    private PodanieService podanieService;

    //@Autowired
    public PodanieFormController(PodanieService podanieService)
    {
        this.podanieService = podanieService;
    }

    //@Secured("ROLE_ADMIN")
    @RequestMapping(value="/podanieForm.html", method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){

        model.addAttribute("podanie",
                id.isPresent()?
                        podanieService.getPodanie(id.get()):
                        new Podanie());

        return "podanieForm";
    }



    //@Secured({"ROLE_DZIEKANAT","ROLE_ADMIN","ROLE_STUDENT"})
    @RequestMapping(value="/podanieForm.html", method= RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String processForm(@Valid @ModelAttribute("podanie") Podanie a, BindingResult errors){

        if(errors.hasErrors()){
            return "podanieForm";
        }

        //log.info("Data utworzenia komponentu "+v.getCreatedDate());
        //log.info("Data edycji komponentu "+new Date());

        podanieService.savePodanie(a);

        return "redirect:podanieList.html";//po udanym dodaniu/edycji przekierowujemy na listę
    }

/*
    @InitBinder
    public void initBinder(WebDataBinder binder) {//Rejestrujemy edytory właściwości

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm");
		dateFormat.setLenient(false);
		CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, false);
		binder.registerCustomEditor(Date.class, "dateStart", dateEditor);
        binder.registerCustomEditor(Date.class, "dateEnd", dateEditor);

        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
        binder.registerCustomEditor(Float.class, "price", new CustomNumberEditor(Float.class, numberFormat, false));

    }
*/
}
