package core.auth;


import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import secondpage.domain.User;
import secondpage.repository.UserRepository;

import javax.persistence.GeneratedValue;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@RequiredArgsConstructor
//@Service
//public class PrincipalDetailsService implements UserDetailsService {
//
//
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String  username) throws UsernameNotFoundException{
//        User user  = userRepository.findByUsername(username);
//
//        if (user == null){
//            return  null;
//        }else{
//            return  new PrincipalDetails(user);
//        }
//
//    }
//}
