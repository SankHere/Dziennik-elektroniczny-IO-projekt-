package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.services.PodanieService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;

@Controller
@Log4j2
public class PodanieListController {

    @Autowired
    private PodanieService podanieService;




    @RequestMapping(value="/podanieList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showPodanieList(Model model, BindingResult result, Pageable pageable){
        model.addAttribute("podanieListPage", podanieService.getAllPodanie(pageable));
        return "podanieList";
    }

    /*
    @Secured("ROLE_ADMIN")
    @GetMapping(path="/podanieList.html", params={"did"})
    public String deletePodanie(long did, HttpServletRequest request){
        podanieService.deletePodanie(did);
        String queryString = prepareQueryString(request.getQueryString());
        return String.format("redirect:podanieList.html%s", queryString);//robimy przekierowanie, ale zachowując parametry pageingu
    }
    */

    @Secured("ROLE_DZIEKANAT")
    @GetMapping(path="/podanieList.html", params={"stan1"})
    public String stan1(Long stan1, HttpServletRequest request){
        podanieService.stan1(stan1);
        String queryString = prepareQueryString(request.getQueryString());
        return String.format("redirect:podanieList.html", queryString);//robimy przekierowanie, ale zachowując parametry pageingu
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(path="/podanieList.html", params={"stan2"})
    public String stan2(Long stan2, HttpServletRequest request){
        podanieService.stan2(stan2);
        String queryString = prepareQueryString(request.getQueryString());
        return String.format("redirect:podanieList.html", queryString);//robimy przekierowanie, ale zachowując parametry pageingu
    }

    @Secured("ROLE_DZIEKANAT")
    @GetMapping(path="/podanieList.html", params={"stan3","stan4"})
    public String stan3(Long stan3, Long stan4, HttpServletRequest request){
        podanieService.stan3(stan3);
        String queryString = prepareQueryString(request.getQueryString());
        return String.format("redirect:podanieList.html", queryString);//robimy przekierowanie, ale zachowując parametry pageingu
    }


//
//    @Secured("ROLE_ADMIN")
//    @GetMapping(path="/podanieList.html", params={"stan4"})
//    public String stan4(Long stan4, HttpServletRequest request){
//        podanieService.stan4(stan4);
//        String queryString = prepareQueryString(request.getQueryString());
//        return String.format("redirect:podanieList.html", queryString);//robimy przekierowanie, ale zachowując parametry pageingu
//    }



    private String prepareQueryString(String queryString) {//np., did=20&page=2&size=20
        if (queryString.contains("&")) {
            return "?"+queryString.substring(queryString.indexOf("&") + 1);//obcinamy parametr did, bo inaczej po przekierowaniu znowu będzie wywołana metoda deleteVihicle
        }else{
            return "";
        }
    }


    /*
    @InitBinder
    public void initBinder(WebDataBinder binder) {//Rejestrujemy edytory właściwości
        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
        CustomNumberEditor priceEditor = new CustomNumberEditor(Float.class, numberFormat, true);
        binder.registerCustomEditor(Float.class, "maxPrice", priceEditor);

    }
*/


}