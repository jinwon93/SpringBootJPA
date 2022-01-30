package jpabook.jpashop.service.instagram;

import jpabook.jpashop.core.handler.ex.CustomApiException;
import jpabook.jpashop.dto.instagram.post.FollowDto;
import jpabook.jpashop.repository.instagram.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final EntityManager em;


    @Transactional
    public void follow(long fromUserId , long toUserId){
        if (followRepository.findFollowByFromUserAndToUserId(fromUserId , toUserId) != null) throw new CustomApiException("이미 팔로우 하셨습니다");
        followRepository.follow(fromUserId ,toUserId);
    }

    public void  unFollow(long fromUserId , long toUserId){
        followRepository.unFollow(fromUserId ,  toUserId);
    }

    public List<FollowDto> getFollower(long profileId , long loginId){
        StringBuffer sb =new StringBuffer();
        sb.append("SELECT u.id, u.name, u.profile_img_url, ");
        sb.append("if ((SELECT 1 FROM follow WHERE from_user_id = ? AND to_user_id = u.id), TRUE, FALSE) AS followState, ");
        sb.append("if ((?=u.id), TRUE, FALSE) AS loginUser ");
        sb.append("FROM user u, follow f ");
        sb.append("WHERE u.id = f.from_user_id AND f.to_user_id = ?");

        //쿼리 완성
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1 , loginId)
                .setParameter(2 , loginId)
                .setParameter(3 , profileId);

        //JPA 쿼리 매핑 = DTO에 매핑
        JpaResultMapper result = new JpaResultMapper();
        List<FollowDto> followDtoList = result.list(query , FollowDto.class);
        return  followDtoList;
    }

    public List<FollowDto> getFollowing(long profileId , long loginId){

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT u.id, u.name, u.profile_img_url, ");
        sb.append("if ((SELECT 1 FROM follow WHERE from_user_id = ? AND to_user_id = u.id), TRUE, FALSE) AS followState, ");
        sb.append("if ((?=u.id), TRUE, FALSE) AS loginUser ");
        sb.append("FROM user u, follow f ");
        sb.append("WHERE u.id = f.to_user_id AND f.from_user_id = ?");


        Query query =  em.createNativeQuery(sb.toString())
                .setParameter(1 , loginId)
                .setParameter(2 , loginId)
                .setParameter(3 , loginId);

        JpaResultMapper result = new JpaResultMapper();
        List<FollowDto> followDtoList = result.list(query , FollowDto.class);
        return followDtoList;
    }


}
