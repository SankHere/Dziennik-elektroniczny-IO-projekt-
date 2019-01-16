package edu.uph.ii.platformy.services.Podania;

import edu.uph.ii.platformy.models.Podania.PodanieSpecjalnosci;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PodanieSpecjalnosciService {


    Page<PodanieSpecjalnosci> getAllPodanieSpecjalnosci(Pageable pageable);

    void deletePodanieSpecjalnosci(Long id);

    PodanieSpecjalnosci getPodanieSpecjalnosci(Long id);

    void savePodanieSpecjalnosci(PodanieSpecjalnosci podanieSpecjalnosci);
}
