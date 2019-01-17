package edu.uph.ii.platformy.repositories.Podania;

import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.models.Podania.KierunekPodanie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KierunekPodanieRepository extends JpaRepository<KierunekPodanie,Long> {

  List<KierunekPodanie> findKierunekPodanieByStatus(int status);

  @Query("UPDATE User SET id_kierunku='"+":idkier" + "'WHERE Id='"+":iduser" + "'")
  void zmienKierunek(@Param("idkier") Long id1 , @Param("iduser") Long id2);
}
