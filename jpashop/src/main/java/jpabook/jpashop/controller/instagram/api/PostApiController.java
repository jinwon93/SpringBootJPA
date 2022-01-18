package jpabook.jpashop.controller.instagram.api;


import jpabook.jpashop.core.auth.PrincipalDetails;
import jpabook.jpashop.service.instagram.LikeService;
import jpabook.jpashop.service.instagram.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostApiController {

    private final PostService postService;
    private final LikeService likeService;

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> postInfo(@PathVariable long postId , @AuthenticationPrincipal PrincipalDetails principalDetails){
        return  new ResponseEntity<>(postService.getPostInfoDto(postId , principalDetails.getUser().getId()) , HttpStatus.OK);
    }

    @PostMapping("/post/{postId}/likes")
    public ResponseEntity<?> likes(@PathVariable long postId , @AuthenticationPrincipal PrincipalDetails principalDetails){
        likeService.likes(postId , principalDetails.getUser().getId());
        return new ResponseEntity<>("좋아요 성공 " ,HttpStatus.OK);
    }
}
