package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Egzamin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EgzaminRepository extends JpaRepository<Egzamin, Integer> {

    //Ubezpieczenie findByUbezpieczenie(Role.Types type);
}
