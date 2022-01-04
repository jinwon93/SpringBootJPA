package jpabook.jpashop.controller.instagram;


import jpabook.jpashop.service.instagram.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post/upload")
    public String upload(){
        return "post/upload";
    }
}
