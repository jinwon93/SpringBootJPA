package jpabook.jpashop.core.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import jpabook.jpashop.domain.instagram.User;
import jpabook.jpashop.service.instagram.UserService;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class jwtUserDatailsService  implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.findUserByEmail(username);

        User user = userOptional.orElseThrow(()->new UsernameNotFoundException("user name not found!"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());

    }
}