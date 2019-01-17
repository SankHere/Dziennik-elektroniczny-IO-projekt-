package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Ocena;
import edu.uph.ii.platformy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OcenaRepository extends JpaRepository<Ocena,Integer> {

    List<Ocena> findByUser(User user);
}
