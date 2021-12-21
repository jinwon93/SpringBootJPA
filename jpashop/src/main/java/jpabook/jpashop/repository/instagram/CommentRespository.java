package jpabook.jpashop.repository.instagram;

import jpabook.jpashop.domain.instagram.Comment;
import jpabook.jpashop.domain.instagram.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRespository extends JpaRepository<Comment , Long> {

    void deleteCommentByPost(Post post);
}
