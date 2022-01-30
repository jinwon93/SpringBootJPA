package jpabook.jpashop.service.instagram;


import jpabook.jpashop.core.handler.ex.CustomApiException;
import jpabook.jpashop.repository.instagram.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public  void  likes(long postId , long sessionId){
        try {
            likeRepository.likes(postId ,  sessionId);
        } catch (Exception e){
            throw new CustomApiException("이미 좋아요 하였습니다");
        }
    }

    @Transactional
    public void  unLikes(long postId ,  long sessionId){
        likeRepository.unLikes(postId ,  sessionId);
    }
}
