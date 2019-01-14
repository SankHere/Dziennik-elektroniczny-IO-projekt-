package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.repositories.StypendiaRepository;
import edu.uph.ii.platformy.repositories.UbezpieczenieRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@Log4j2
public class UbezpieczeniaListController {

    @Autowired
    private UbezpieczenieRepository ubezpieczenieRepository;

    @RequestMapping(value="/ubezpieczeniaList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showUbezpieczeniaList(Model model){
        model.addAttribute("ubezpieczenia", ubezpieczenieRepository.findAll());
        return  "ubezpieczeniaList";
    }

}




