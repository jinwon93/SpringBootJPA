package jpabook.jpashop.service.instagram;


import jpabook.jpashop.core.handler.CustomApiException;
import jpabook.jpashop.domain.instagram.Comment;
import jpabook.jpashop.domain.instagram.Post;
import jpabook.jpashop.domain.instagram.User;
import jpabook.jpashop.repository.instagram.CommentRespository;
import jpabook.jpashop.repository.instagram.PostRepository;
import jpabook.jpashop.repository.instagram.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRespository  commentRespository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public Comment addComment (String text , long postId , long sessionId){
        Post post = postRepository.findById(postId).get();
        User user = userRepository.findById(sessionId).orElseThrow(() -> {
            return new CustomApiException("유저 아이디를 찾을 수 없습니다.");
        });

        return Comment.builder().build();
    }
}
