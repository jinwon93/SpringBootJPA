package secondpage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import secondpage.domain.likes.Likes;
import secondpage.domain.post.Post;

public interface LikeRepository extends JpaRepository<Likes , Long> {

    void deleteLikesByPost(Post post);

    void likes(long postId , long userId);

    void unLikes(long postId , long userId);
}
