package edu.uph.ii.platformy.services;


import edu.uph.ii.platformy.controllers.commands.KierunekFilter;
import edu.uph.ii.platformy.exceptions.KierunkiNotFoundException;
import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.repositories.KierunkiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class KierunkiServiceImpl implements KierunekService {

    @Autowired
    private KierunkiRepository kierunkiRepository;

    @Override
    public Page<Kierunki> getAllKierunki(KierunekFilter search, Pageable pageable) {
        Page page;
        if(search.isEmpty()){
            page = kierunkiRepository.findAll(pageable);
        }else{
            page = kierunkiRepository.findAllKierunkiUsingFilter(search.getPhraseLIKE(), search.getLiczbaMiejsc(), pageable);
        }
        return page;
    }



    @Transactional
    @Override
    public Kierunki getKierunki(Long id) {
        Optional<Kierunki> optionalKierunki = kierunkiRepository.findById(id);
        Kierunki kierunki = optionalKierunki.orElseThrow(()->new KierunkiNotFoundException(id));
       // produkt.getAkcesorias().size();//dociągnięcie leniwych akcesoriów. Wymagana adnotacja @Transaction nad metodą lub klasą, aby findById nie zamknęło transakcji
        return kierunki;
    }

    @Override
    public void deleteKierunki(Long id) {
        // w przypadku usuwania obsługa wyjątku VehicleNotFoundException nie jest niezbędna dla bezpieczeństwa systemu
        if(kierunkiRepository.existsById(id)){
            //Set<ProduktZamowienie> przam = produktZamowienieRepository.findAllByProdukt(produktRepository.findById(id).get());
            //for(ProduktZamowienie p : przam)
            //    produktZamowienieRepository.delete(p);
            kierunkiRepository.deleteById(id);
        }else{
            throw new KierunkiNotFoundException(id);
        }
    }

    @Override
    public void saveKierunki(Kierunki kierunki) {
        kierunkiRepository.save(kierunki);
    }
}
