package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.models.Specjalnosci;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecjalnosciRepository extends JpaRepository<Specjalnosci, Long> {

    Specjalnosci findByName(String name);

    Specjalnosci findById(Specjalnosci id);

    List<Specjalnosci> findSpecjalnosciByKierunki(Kierunki kierunki);

}

