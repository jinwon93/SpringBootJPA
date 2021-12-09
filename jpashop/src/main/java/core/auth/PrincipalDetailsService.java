package core.auth;


//import org.springframework.security.core.userdetails.UserDetails;


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
