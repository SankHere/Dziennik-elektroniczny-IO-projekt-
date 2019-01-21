package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.models.Podania.StypendiumPodanie;
import edu.uph.ii.platformy.models.Stypendia;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.KierunkiRepository;
import edu.uph.ii.platformy.repositories.Podania.StypendiumPodanieRepository;
import edu.uph.ii.platformy.repositories.StypendiaRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@Log4j2
public class StypendiaListController {

    @Autowired
    private StypendiaRepository stypendiaRepository;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private KierunkiRepository kierunkiRepository;

//    @Autowired
//    private StypendiumPodanieRepository stypendiumPodanieRepository;

//    @Autowired
//    private StypendiumPodanie stypendiumPodanie;

    //pobrac id zalogowanego studenta i sprawdzic czy w id_stypendium jest 4 jesli nie to formularz i lista , jesli tak to wypisac jego stypednium
    @RequestMapping(value="/stypendiaList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showStypendiaList(Model model) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleName = authentication.getName();

        User zalogowany = userRepository.findByUsername(currentPrincipleName);

        Stypendia stypendia = zalogowany.getStypendia();
        model.addAttribute("moje", stypendia.getName());
        model.addAttribute("moje2" , stypendia.getKwota());
       // Long test2 = stypendiumPodanie.getId();

        if (zalogowany.getStypendia().getId() == 4) {

            model.addAttribute("zalogowany", zalogowany);

            model.addAttribute("stypendia", stypendiaRepository.findAll());
           model.addAttribute("test", 1);
            return "stypendiaList";
        } else {


//            Optional<Stypendia> st = stypendiaRepository.findById(test2); //znalezienie takiego stypendium o id z podania
//            if ( st.isPresent()) { //sprawdzanie czy istnieja w bazie
//                //pobranie obiektu user
//
//
//                Stypendia stypendia = st.get(); //pobieram obiekt stypendia
//
//
//                model.addAttribute("stypendium", stypendia);
//            }
//            model.addAttribute("stypendium", stypendiaRepository.getOne().getId());
            String zmienna = "nie";
            model.addAttribute("zalogowany", zalogowany);
            model.addAttribute("test",2);

            return "stypendiaList";
        }
    }

}




