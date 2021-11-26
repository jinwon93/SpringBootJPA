package secondpage.service;

import secondpage.domain.User;
import secondpage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
//public class JwtMemberDatailsService implements UserDetailsService {
//
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public  UserDetails  loadUserByUsername(String username) throws UsernameNotFoundException{
//        User user = userRepository.findAllByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
//        return user;
//    }
//}
