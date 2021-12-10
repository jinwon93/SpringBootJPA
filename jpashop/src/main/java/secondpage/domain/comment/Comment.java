package secondpage.domain.comment;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import secondpage.domain.post.Post;
import secondpage.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JsonIgnoreProperties({"postList"})
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private LocalDateTime createDate;


    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

    @Builder
    public Comment(String text, User user, Post post) {
        this.text = text;
        this.user = user;
        this.post = post;
    }
}
