package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.models.Specjalnosci;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpecjalnosciRepository extends JpaRepository<Specjalnosci, Long> {

    Specjalnosci findByName(String name);



    @Query("SELECT k FROM Specjalnosci k WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR "+
            "upper(k.name) LIKE upper(:phrase) " +
            ") " )
    Page<Specjalnosci> findAllSpecjalnosciUsingFilter(@Param("phrase") String k, Pageable pageable);


}

