package jpabook.jpashop.service.instagram;

import jpabook.jpashop.core.handler.CustomApiException;
import jpabook.jpashop.repository.instagram.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final EntityManager em;


    @Transactional
    public void follow(long fromUserId , long toUserId){
        if (followRepository.findFollowByFromUserAndToUserId(fromUserId , toUserId) != null) throw new CustomApiException("이미 팔로우 하셨습니다");
        followRepository.
    }
}
