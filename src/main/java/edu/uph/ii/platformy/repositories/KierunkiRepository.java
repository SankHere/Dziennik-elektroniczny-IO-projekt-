package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Kierunki;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KierunkiRepository extends JpaRepository<Kierunki, Integer> {

    Kierunki findByName(String name);

}
