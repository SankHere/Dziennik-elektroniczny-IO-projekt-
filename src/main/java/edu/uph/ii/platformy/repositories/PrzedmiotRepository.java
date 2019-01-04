package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Przedmiot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrzedmiotRepository extends JpaRepository<Przedmiot, Integer> {

    //Ubezpieczenie findByUbezpieczenie(Role.Types type);
}
