package secondpage.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import secondpage.repository.UserRepository;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


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

}
