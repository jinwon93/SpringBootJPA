package jpabook.jpashop.repository.instagram;

import org.springframework.data.jpa.repository.JpaRepository;
import jpabook.jpashop.domain.instagram.Likes;
import jpabook.jpashop.domain.instagram.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikeRepository extends JpaRepository<Likes , Long> {

    void deleteLikesByPost(Post post);

    @Modifying
    @Query(value = "INSERT INTO likes(post_id , user_id) VALUES(:postId , : userId)" , nativeQuery = true)
    void likes(long postId , long userId);

    void unLikes(long postId , long userId);
}
