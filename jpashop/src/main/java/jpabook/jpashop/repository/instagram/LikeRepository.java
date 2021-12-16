package jpabook.jpashop.repository.instagram;

import org.springframework.data.jpa.repository.JpaRepository;
import jpabook.jpashop.domain.instagram.Likes;
import jpabook.jpashop.domain.instagram.Post;

public interface LikeRepository extends JpaRepository<Likes , Long> {

    void deleteLikesByPost(Post post);

    void likes(long postId , long userId);

    void unLikes(long postId , long userId);
}
