package edu.uph.ii.platformy.repositories.Podania;

import edu.uph.ii.platformy.models.Podania.PodanieSpecjalnosci;
import edu.uph.ii.platformy.models.Specjalnosci;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PodanieSpecjalnosciRepository extends JpaRepository<PodanieSpecjalnosci, Long> {

    /*
    @Query("SELECT k FROM Specjalnosci k WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR "+
            "upper(k.name) LIKE upper(:phrase) " +
            ") " )
    Page<Specjalnosci> delete(@Param("phrase") String k, Pageable pageable);
*/


    @Query("UPDATE User SET id_specjalnosci='" + ":id" + "' WHERE id='" + ":iduser" + "'"
             )
    void zmienSpecjalnosc(@Param("idspec") Long id1, @Param("iduser") Long id2);


}

