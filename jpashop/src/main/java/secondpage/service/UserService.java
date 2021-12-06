package secondpage.service;


import core.handler.CustomVaildationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import secondpage.domain.User;
import secondpage.domain.UserLoginDto;
import secondpage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void save(UserLoginDto userLoginDto){


        if (userRepository.findSUerByEmail(userLoginDto.getEmail()) != null){
            new CustomVaildationException("이미 존자하는 회원입니다");
        }
        userRepository.save(User.builder()
                .email(userLoginDto.getEmail())
                .password(userLoginDto.getPassword())
                .phone(userLoginDto.getPhone())
                .name(userLoginDto.getName())
                .build()
                );
    }

    @Value("${profileImg.path}")
    private String uploadFolder;


}
