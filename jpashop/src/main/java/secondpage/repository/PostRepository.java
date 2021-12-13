package secondpage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import secondpage.domain.post.Post;

public interface PostRepository  extends JpaRepository<Post  , Long> {


}
