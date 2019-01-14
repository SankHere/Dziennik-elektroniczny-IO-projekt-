package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Podanie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PodanieRepository extends JpaRepository<Podanie, Long> {


/*
    @Query("SELECT k FROM Specjalnosci k WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR "+
            "upper(k.name) LIKE upper(:phrase) " +
            ") " )
    Page<Specjalnosci> findAllSpecjalnosciUsingFilter(@Param("phrase") String k, Pageable pageable);
*/

}

