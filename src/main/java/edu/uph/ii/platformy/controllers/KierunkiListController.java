package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.controllers.commands.KierunekFilter;
import edu.uph.ii.platformy.services.KierunekService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@SessionAttributes("searchCommand")
@Log4j2
public class KierunkiListController {

    @Autowired
    private KierunekService kierunekService;



    @GetMapping(value="/error")
    public String resetKierunkiList(){
        return "redirect:kierunkiList.html";
    }


    @ModelAttribute("searchCommand")
    public KierunekFilter getSimpleSearch(){
        return new KierunekFilter();
    }

    @GetMapping(value="/kierunkiList.html", params = {"all"})
    public String resetProduktList(@ModelAttribute("searchCommand") KierunekFilter search){
        search.clear();
        return "redirect:kierunkiList.html";
    }

//    public List<Akcesoria> loadAkcesoria(){
//        List<Akcesoria> akcesorias = akcesoriaRepository.findAll();
//        return akcesorias;
//    }

    @RequestMapping(value="/kierunkiList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showProduktList(Model model, @Valid @ModelAttribute("searchCommand") KierunekFilter search, BindingResult result, Pageable pageable){

        model.addAttribute("kierunkiListPage", kierunekService.getAllKierunki(search, pageable));

        //model.addAttribute("kategoriaListPage", kategoriaRepository.findAll());
        return "kierunkiList";
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
        binder.registerCustomEditor(Float.class, "minPrice", priceEditor);
        binder.registerCustomEditor(Float.class, "maxPrice", priceEditor);

    }

    @GetMapping(value = "/delete.html")
    public String deleteKierunki(Model model, @RequestParam(value = "id", required = false) Long id) {
        kierunekService.deleteKierunki(id);
        return "redirect:kierunkiList.html";
    }
}




