package jpabook.jpashop.controller.instagram.api;


import jpabook.jpashop.core.auth.PrincipalDetails;
import jpabook.jpashop.service.instagram.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FollowApiController {

    private final FollowService followService;


    @PostMapping("/follow/{toUserId}")
    public ResponseEntity<?> followUser(@PathVariable long toUserId  , @AuthenticationPrincipal PrincipalDetails principalDetails){
        followService.follow(principalDetails.getUser().getId() , toUserId);
        return new ResponseEntity<>("팔로우 성공" , HttpStatus.OK);
    }

    public ResponseEntity<?> unFollowUser(@PathVariable long toUserId , @AuthenticationPrincipal PrincipalDetails principalDetails){
        followService.unFollow(principalDetails.getUser().getId() , toUserId);
        return new ResponseEntity<>("팔로우 최소 성공 " , HttpStatus.OK);
    }
}
