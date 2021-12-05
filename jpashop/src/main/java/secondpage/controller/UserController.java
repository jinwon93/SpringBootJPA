package secondpage.controller;


import core.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import secondpage.domain.User;
import secondpage.repository.UserRepository;

import java.util.Collections;
import java.util.Map;



@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final PrincipalDetails jwtTokenProvider;
    private final UserRepository userRepository;

    // 회원가입


}
