package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Kierunki;
import edu.uph.ii.platformy.models.Role;
import edu.uph.ii.platformy.models.Stypendia;
import edu.uph.ii.platformy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String name);

    List<User> findByRoles(Role role);

    List<User> findByKierunki(Kierunki kierunki);

    List<User> findByStypendia(Stypendia stypendia);


//    @Query("SELECT distinct u FROM User u inner join u.roles r WHERE :name = r.name")
//    List<User> findByRole(@Param("name") String name);
}
