package edu.uph.ii.platformy.repositories.Podania;

import edu.uph.ii.platformy.models.Podania.KierunekPodanie;
import edu.uph.ii.platformy.models.Podania.StypendiumPodanie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StypendiumPodanieRepository  extends JpaRepository<StypendiumPodanie,Long> {
    
    List<StypendiumPodanie> findStypendiumPodanieByStatus(int status);
}
