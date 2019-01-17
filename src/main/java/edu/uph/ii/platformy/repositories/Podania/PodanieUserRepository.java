package edu.uph.ii.platformy.repositories.Podania;

import edu.uph.ii.platformy.models.Podania.KierunekPodanie;
import edu.uph.ii.platformy.models.Podania.PodanieSpecjalnosci;
import edu.uph.ii.platformy.models.Podania.PodanieUbezpieczenie;
import edu.uph.ii.platformy.models.Podania.PodanieUser;
import edu.uph.ii.platformy.models.Specjalnosci;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PodanieUserRepository extends JpaRepository<PodanieUser, Long> {

    List<PodanieUser> findByStatus(int status);


}

