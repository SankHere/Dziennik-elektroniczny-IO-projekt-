package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Kierunki;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KierunkiRepository extends JpaRepository<Kierunki, Long> {


    Kierunki findByName(String name);

    @Query("SELECT kier FROM Kierunki kier WHERE kier.status = :status ")
    List<Kierunki> findAllKierunkiUsingStatus(@Param("status") int status);

//    @Query("SELECT k FROM Kierunki k WHERE " +
//            "(" +
//            ":phrase is null OR :phrase = '' OR "+
//            "upper(k.name) LIKE upper(:phrase) " +
//            ") " +
//            "AND " +
//            "(:liczbaMiejsc is null OR :liczbaMiejsc >= k.liczbaMiejsc) " )
//    Page<Kierunki> findAllKierunkiUsingFilter(@Param("phrase") String k, @Param("liczbaMiejsc") int liczbaMiejsc, Pageable pageable);

}
