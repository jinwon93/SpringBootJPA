package jpabook.jpashop.controller;

import jpabook.jpashop.dto.instagram.user.UserSignupDto;
import jpabook.jpashop.service.instagram.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class AccountController {

    private final UserService userService;

    //회원가입
    @PostMapping("/signup")
    public String signUp(@Valid UserSignupDto userSignupDto , BindResult bindResult){
        userService.save(userSignupDto);
        return  "redirect:/login";
    }
}
