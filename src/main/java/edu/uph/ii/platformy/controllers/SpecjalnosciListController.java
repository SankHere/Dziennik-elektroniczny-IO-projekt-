package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.controllers.commands.SpecjalnoscFilter;
import edu.uph.ii.platformy.models.Specjalnosci;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.Podania.PodanieSpecjalnosciRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import edu.uph.ii.platformy.services.SpecjalnosciService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DecimalFormat;

@Controller
@SessionAttributes("searchCommand")
@Log4j2
public class SpecjalnosciListController {

    @Autowired
    private SpecjalnosciService specjalnosciService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PodanieSpecjalnosciRepository podanieSpecjalnosciRepository;


    @RequestMapping(value="/specjalnosciList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showSpecjalnosciList(Model model, Pageable pageable){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User zalogowany = userRepository.findByUsername(currentPrincipalName);
        model.addAttribute("zalogowany", zalogowany);

        Specjalnosci specjalnosci = zalogowany.getSpecjalnosci();

        if(specjalnosci.getId()==4){
            model.addAttribute("specjalnosciListPage", specjalnosciService.getAllSpecjalnosci(pageable));
            return "specjalnosciList";

        }


        else {
            return "redirect:";
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(path="/specjalnosciList.html", params={"did"})
    public String deleteSpecjalnosci(Long did, HttpServletRequest request){
        specjalnosciService.deleteSpecjalnosci(did);
        String queryString = prepareQueryString(request.getQueryString());
        return String.format("redirect:specjalnosciList.html%s", queryString);//robimy przekierowanie, ale zachowując parametry pageingu
    }

    private String prepareQueryString(String queryString) {//np., did=20&page=2&size=20
        if (queryString.contains("&")) {
            return "?"+queryString.substring(queryString.indexOf("&") + 1);//obcinamy parametr did, bo inaczej po przekierowaniu znowu będzie wywołana metoda deleteVihicle
        }else{
            return "";
        }
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {//Rejestrujemy edytory właściwości
        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
        CustomNumberEditor priceEditor = new CustomNumberEditor(Float.class, numberFormat, true);
        binder.registerCustomEditor(Float.class, "maxPrice", priceEditor);

    }



}