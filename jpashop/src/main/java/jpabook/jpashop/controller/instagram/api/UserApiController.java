package jpabook.jpashop.controller.instagram.api;


import jpabook.jpashop.core.auth.PrincipalDetails;
import jpabook.jpashop.service.instagram.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserApiController {

    private final FollowService followService;

    @GetMapping("/user/{profileId}/follower")
    public ResponseEntity<?> getFollower(@PathVariable long profileId , @AuthenticationPrincipal PrincipalDetails principalDetails){
        return new ResponseEntity<>(followService.getFollower(profileId , principalDetails.getUser().getId()) , HttpStatus.OK);
    }
}
