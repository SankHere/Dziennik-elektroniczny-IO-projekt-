package edu.uph.ii.platformy.services;



import edu.uph.ii.platformy.controllers.commands.KierunekFilter;
import edu.uph.ii.platformy.models.Kierunki;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KierunekService {


    Page<Kierunki> getAllKierunki(KierunekFilter filter, Pageable pageable);

    Kierunki getKierunki(Long id);

    void deleteKierunki(Long id);

    void saveKierunki(Kierunki kierunki);
}
