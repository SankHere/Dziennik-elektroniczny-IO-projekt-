package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Specjalnosci;
import edu.uph.ii.platformy.models.Stypendia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StypendiaRepository extends JpaRepository<Stypendia, Long> {

    Stypendia findByName(String name);

}

