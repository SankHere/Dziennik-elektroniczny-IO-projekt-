package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.models.Przedmiot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrzedmiotRepository extends JpaRepository<Przedmiot, Integer> {

    List<Przedmiot> findPrzedmiotByKierunki(Kierunki kierunki);
}
