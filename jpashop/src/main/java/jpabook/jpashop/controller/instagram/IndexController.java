package jpabook.jpashop.controller.instagram;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    
    //회원가입 폼으로 이동
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    //로그인 화면으로 이동
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping({"/" , "post/story"})
    public String story(){
        return "post/story";
    }
}
