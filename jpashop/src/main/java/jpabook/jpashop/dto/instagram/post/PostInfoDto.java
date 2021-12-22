package jpabook.jpashop.dto.instagram.post;


import jpabook.jpashop.domain.instagram.Comment;
import jpabook.jpashop.domain.instagram.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
@Getter
@Setter
@NoArgsConstructor
public class PostInfoDto {


    private long id;
    private String text;
    private String tag;
    private LocalDateTime createDate;
    private User postUploader;
    private long likeCount;
    private boolean likeState;
    private boolean uploader;
    private String postImgUrl;
    private List<Comment> commentList;



    public void getCreateDate(LocalDateTime createDate) {
    }
}
