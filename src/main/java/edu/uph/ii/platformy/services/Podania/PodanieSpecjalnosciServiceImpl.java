package edu.uph.ii.platformy.services.Podania;


import edu.uph.ii.platformy.exceptions.PodanieNotFoundException;
import edu.uph.ii.platformy.models.Podania.PodanieSpecjalnosci;
import edu.uph.ii.platformy.repositories.Podania.PodanieSpecjalnosciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PodanieSpecjalnosciServiceImpl implements PodanieSpecjalnosciService {

    @Autowired
    private PodanieSpecjalnosciRepository podanieSpecjalnosciRepository;


    @Override
    public Page<PodanieSpecjalnosci> getAllPodanieSpecjalnosci(Pageable pageable) {
        Page page;
            page = podanieSpecjalnosciRepository.findAll(pageable);
        return page;
    }





    @Transactional
    @Override
    public PodanieSpecjalnosci getPodanieSpecjalnosci(Long id) {

        Optional<PodanieSpecjalnosci> optionalAccessory = podanieSpecjalnosciRepository.findById(id); //long na int
        PodanieSpecjalnosci podanieSpecjalnosci = optionalAccessory.orElseThrow(()->new PodanieNotFoundException(id));
        //accessory.getName().size();//dociągnięcie leniwych akcesoriów. Wymagana adnotacja @Transaction nad metodą lub klasą, aby findById nie zamknęło transakcji
        return podanieSpecjalnosci;
    }

    @Override
    public void deletePodanieSpecjalnosci(long id1, long id2) {
        // w przypadku usuwania obsługa wyjątku VehicleNotFoundException nie jest niezbędna dla bezpieczeństwa systemu



        if(podanieSpecjalnosciRepository.existsById(id1) == true){
            podanieSpecjalnosciRepository.zmienSpecjalnosc(id1,id2);
            podanieSpecjalnosciRepository.deleteById(id1);
        }else{
            throw new PodanieNotFoundException(id1);
        }
    }

    @Override
    public void savePodanieSpecjalnosci(PodanieSpecjalnosci podanieSpecjalnosci) {
        podanieSpecjalnosciRepository.save(podanieSpecjalnosci);
    }
}
