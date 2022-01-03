package jpabook.jpashop.controller.instagram;

import jpabook.jpashop.dto.instagram.user.UserSignupDto;
import jpabook.jpashop.service.instagram.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    public String logoutPage(HttpServletRequest request , HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request , response , SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}
