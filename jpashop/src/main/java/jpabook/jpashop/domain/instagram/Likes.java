package jpabook.jpashop.domain.instagram;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Likes {


    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    @JoinColumn("post_id")
    private Post post;


    @JsonIgnoreProperties({"postList"}) //post -> user -> likesList -> user -> postList 무한 참조 막기 위함
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;


    @Builder
    public Likes(Long id, Post post, User user) {
        this.id = id;
        this.post = post;
        this.user = user;
    }
}
