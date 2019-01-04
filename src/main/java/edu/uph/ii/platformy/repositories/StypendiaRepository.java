package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Stypendia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StypendiaRepository extends JpaRepository<Stypendia, Integer> {

    //Ubezpieczenie findByUbezpieczenie(Role.Types type);
}

