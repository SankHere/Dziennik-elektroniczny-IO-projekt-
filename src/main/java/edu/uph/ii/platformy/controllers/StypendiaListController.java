package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.repositories.StypendiaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@Log4j2
public class StypendiaListController {

    @Autowired
    private StypendiaRepository stypendiaRepository;

    @RequestMapping(value="/stypendiaList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showStypendiaList(Model model){
        model.addAttribute("stypendia", stypendiaRepository.findAll());
        return  "stypendiaList";
    }

}




