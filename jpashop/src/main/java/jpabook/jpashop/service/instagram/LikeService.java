package jpabook.jpashop.service.instagram;


import jpabook.jpashop.repository.instagram.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public  void  likes(long postId , long sessionId){
        try {
            likeRepository.likes(postId ,  sessionId);
        } catch (Exception e){
            throw new Exception("ddd");
        }
    }
}
