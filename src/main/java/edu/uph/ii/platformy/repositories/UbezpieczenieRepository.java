package edu.uph.ii.platformy.repositories;


import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.models.Ubezpieczenie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbezpieczenieRepository extends JpaRepository<Ubezpieczenie, Long> {

    Ubezpieczenie findByName(String name);
}
