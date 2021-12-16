package jpabook.jpashop.domain.instagram;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jpabook.jpashop.domain.instagram.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Post {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;


    private String postImgUrl;
    private String tag;
    private String text;


    @JsonIgnoreProperties({"postList"})
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;



    @JsonIgnoreProperties({"post"})
    @OneToMany(mappedBy = "post")
    private List<Likes> likesList;



    @OrderBy("id")
    @JsonIgnoreProperties({"post"})
    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;


    @Transient
    private long likesCount;


    @Transient
    private boolean likesState;


    @Builder
    public Post(String postImgUrl, String tag, String text, User user, long likesCount) {
        this.postImgUrl = postImgUrl;
        this.tag = tag;
        this.text = text;
        this.user = user;
        this.likesCount = likesCount;
    }

    public void  update(String tag  , String text){
        this.tag = tag;
        this.text = text;
    }

    public void  updateLikesCount(long likesCount){
        this.likesCount = likesCount;
    }

    public void  updateLikesState(boolean likesState){
        this.likesState = likesState;
    }

    public void makePost(long id){
        this.id = id;
    }
}

