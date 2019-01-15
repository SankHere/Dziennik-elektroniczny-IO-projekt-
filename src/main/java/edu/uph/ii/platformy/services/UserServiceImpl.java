package edu.uph.ii.platformy.services;


import edu.uph.ii.platformy.config.ProfileNames;
import edu.uph.ii.platformy.exceptions.UserNotFoundException;
import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by grzesiek on 23.08.2017.
 */
@Service("userDetailsService")
@Profile(ProfileNames.DATABASE)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EgzaminRepository egzaminRepository;

//    @Override
//    public List<Egzamin> getAllTypes() {
//        return egzaminRepository.findAll();
//    }

//    @Override
//    public Page<edu.uph.ii.platformy.models.User> getAllUser(Pageable pageable) {
//        Page page;
//
//        page = userRepository.findAll(pageable);
//        //   page = accessoryRepository.findAllAccessoriesUsingFilter(pageable);
//
//
//        return page;
//
//    }


    @Override
    //bez adnotacji @Transactional sesja jest zamykana po wywołaniu findByUsername, co uniemożliwia dociągnięcie ról, gdyż fetch=EAGER.
    //ponadto, musi być włączone zarządzanie transakcjami @EnableTransactionManagement
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        edu.uph.ii.platformy.models.User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return createUserDetails(user);
    }

    private UserDetails createUserDetails(edu.uph.ii.platformy.models.User user) {
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (Role role : user.getRoles()){
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getType().toString()));
//        }

        Set<GrantedAuthority> grantedAuthorities =
                user.getRoles().stream().map(//mapowanie Role na GrantedAuthority
                        r -> new SimpleGrantedAuthority(r.getType().toString())
                ).collect(Collectors.toSet());
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, grantedAuthorities);
    }

    @Override
    public void save(edu.uph.ii.platformy.models.User user) {

        Role userRole = roleRepository.findRoleByType(Role.Types.ROLE_USER);
        List roles = Collections.singletonList(userRole);
        user.setRoles(new HashSet<>(roles));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPasswordConfirm(null);//wyzerowanie jest potrzebne ze względu na walidację
        userRepository.saveAndFlush(user);
    }

    @Override
    public boolean isUniqueLogin(String username) {
        return userRepository.findByUsername(username) == null;
    }

//    @Transactional
//    @Override
//    public edu.uph.ii.platformy.models.User getUser(Long id) {
//        Optional<edu.uph.ii.platformy.models.User> optionalUser = userRepository.findById(id);
//        edu.uph.ii.platformy.models.User user = optionalUser.orElseThrow(()->new UserNotFoundException(id));
//        //user.getAccessories().size();//dociągnięcie leniwych akcesoriów. Wymagana adnotacja @Transaction nad metodą lub klasą, aby findById nie zamknęło transakcji
//        return user;
//    }


}