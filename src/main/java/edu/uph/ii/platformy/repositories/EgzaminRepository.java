package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Egzamin;
import edu.uph.ii.platformy.models.Przedmiot;
import edu.uph.ii.platformy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EgzaminRepository extends JpaRepository<Egzamin, Long> {

    //Ubezpieczenie findByUbezpieczenie(Role.Types type);

    @Query("SELECT e FROM Egzamin e join e.przedmiot p where p in :przedmioty")
    List<Egzamin> findByListPrzedmiotu(@Param("przedmioty")List<Przedmiot> przedmiots);
}
