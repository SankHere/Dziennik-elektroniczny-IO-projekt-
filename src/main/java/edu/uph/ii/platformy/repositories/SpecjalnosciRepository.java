package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Specjalnosci;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecjalnosciRepository extends JpaRepository<Specjalnosci, Integer> {

    Specjalnosci findBySpecjalnoscname(String name);
}

