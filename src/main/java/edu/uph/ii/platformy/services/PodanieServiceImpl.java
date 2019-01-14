package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.exceptions.PodanieNotFoundException;
import edu.uph.ii.platformy.models.Podanie;
import edu.uph.ii.platformy.repositories.PodanieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PodanieServiceImpl implements PodanieService {

    @Autowired
    private PodanieRepository podanieRepository;


    @Override
    public Page<Podanie> getAllPodanie(Pageable pageable) {
        Page page;

            page = podanieRepository.findAll(pageable);
       //   page = accessoryRepository.findAllAccessoriesUsingFilter(pageable);


        return page;

    }

    @Transactional
    @Override
    public Podanie getPodanie(Long id) {

        Optional<Podanie> optionalAccessory = podanieRepository.findById(id); //long na int
        Podanie podanie = optionalAccessory.orElseThrow(()->new PodanieNotFoundException(id));
        return podanie;
    }



    @Override
    public void savePodanie(Podanie podanie) {
        podanieRepository.save(podanie);
    }
}
