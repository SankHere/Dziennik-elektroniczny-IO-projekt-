package edu.uph.ii.platformy.services.Podania;

import edu.uph.ii.platformy.models.Podania.PodanieSpecjalnosci;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PodanieSpecjalnosciService {


    Page<PodanieSpecjalnosci> getAllPodanieSpecjalnosci(Pageable pageable);

    PodanieSpecjalnosci getPodanieSpecjalnosci(Long id);

    void deletePodanieSpecjalnosci(long id1, long id2);

    void savePodanieSpecjalnosci(PodanieSpecjalnosci podanieSpecjalnosci);
}
