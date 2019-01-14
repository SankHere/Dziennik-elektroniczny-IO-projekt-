package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Podanie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PodanieService {


    Page<Podanie> getAllPodanie(Pageable pageable);

    Podanie getPodanie(Long id);

    void stan1(Long id);
    void stan2(Long id);
    void stan3(Long id);
    void stan4(Long id);

    void savePodanie(Podanie podanie);
}
