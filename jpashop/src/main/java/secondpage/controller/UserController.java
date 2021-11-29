package secondpage.controller;


import core.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import secondpage.domain.User;
import secondpage.repository.UserRepository;

import java.util.Map;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @RequestMapping(value = "/user")
    public String userMain() {
        return "/user/main";
    }

    @RequestMapping(value = "/customer")
    public String customerMain() { return "/customer/main"; }

    @RequestMapping(value = "/signup")
    public String signup() {
        return "/signup";
    }

    @RequestMapping(value = "/signupGroup")
    public String group() {
        return "signupGroup";
    }

    @RequestMapping(value = "/login")
    public String login() { return "login"; }

    @PostMapping("/checkemail")
    @ResponseBody
    public int checkEmail(String email) {
        if(userRepository.countByEmail(email) > 0){
            return 0;
        };
        return 1;
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        User member = userRepository.findByEmail(user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtProvider.createToken(member.getUsername(), member.getRoles());
    }
}
