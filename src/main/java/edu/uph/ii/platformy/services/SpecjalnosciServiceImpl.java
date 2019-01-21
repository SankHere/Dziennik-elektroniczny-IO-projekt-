package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.SpecjalnoscFilter;
import edu.uph.ii.platformy.exceptions.SpecjalnosciNotFoundException;
import edu.uph.ii.platformy.models.Specjalnosci;
import edu.uph.ii.platformy.repositories.SpecjalnosciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SpecjalnosciServiceImpl implements SpecjalnosciService {

    @Autowired
    private SpecjalnosciRepository specjalnosciRepositry;


    @Transactional
    @Override
    public Specjalnosci getSpecjalnosci(Long id) {

        Optional<Specjalnosci> optionalSpecjalnosci = specjalnosciRepositry.findById(id); //long na int
        Specjalnosci specjalnosci = optionalSpecjalnosci.orElseThrow(()->new SpecjalnosciNotFoundException(id));
        return specjalnosci;

    }

    @Override
    public void saveSpecjalnosci(Specjalnosci specjalnosci) {
        specjalnosciRepositry.save(specjalnosci);
    }
}
