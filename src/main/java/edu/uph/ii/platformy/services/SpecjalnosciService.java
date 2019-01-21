package edu.uph.ii.platformy.services;


import edu.uph.ii.platformy.controllers.commands.SpecjalnoscFilter;
import edu.uph.ii.platformy.models.Specjalnosci;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SpecjalnosciService {




    Specjalnosci getSpecjalnosci(Long id);


    void saveSpecjalnosci(Specjalnosci specjalnosci);
}
