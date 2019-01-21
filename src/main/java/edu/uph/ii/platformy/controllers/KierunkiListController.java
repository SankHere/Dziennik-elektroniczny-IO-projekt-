package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.repositories.KierunkiRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@Log4j2
public class KierunkiListController {

    @Autowired
    private KierunkiRepository kierunkiRepository;


    @RequestMapping(value="/kierunkiList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showKierunkiList(Model model){

        List<Kierunki> kierunki = kierunkiRepository.findAllKierunkiUsingStatus(2);

        model.addAttribute("kierunki", kierunki );
        return  "kierunkiList";
    }
}




