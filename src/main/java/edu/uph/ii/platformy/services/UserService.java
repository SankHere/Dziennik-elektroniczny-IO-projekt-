package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Egzamin;
import edu.uph.ii.platformy.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
// Własne metody
    void save(User user);

    boolean isUniqueLogin(String login);
    //User getUser(Long id);
//    Page<User> getAllUser(Pageable pageable);
//    List<Egzamin> getAllTypes();


    //void saveUser(User user);
}
