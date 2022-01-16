package jpabook.jpashop.controller.instagram.api;


import jpabook.jpashop.core.auth.PrincipalDetails;
import jpabook.jpashop.domain.instagram.Comment;
import jpabook.jpashop.dto.instagram.comment.CommentUploadDto;
import jpabook.jpashop.service.instagram.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/commnet")
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentUploadDto commentUploadDto , BindingResult bindingResult , @AuthenticationPrincipal PrincipalDetails principalDetails){
        ResponseEntity<Comment> commentResponseEntity = new ResponseEntity<>(commentService.addComment(commentUploadDto.getText(), commentUploadDto.getPostId(), principalDetails.getUser().getId()), HttpStatus.OK);
        return commentResponseEntity;
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>("댓글 삭제 성공" , HttpStatus.OK);
    }
}
