package jpabook.jpashop.controller.instagram;


import jpabook.jpashop.core.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jpabook.jpashop.repository.instagram.UserRepository;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final PrincipalDetails jwtTokenProvider;
    private final UserRepository userRepository;

    // 회원가입


}
