package jpabook.jpashop.dto.instagram.post;


import jpabook.jpashop.domain.instagram.Comment;
import jpabook.jpashop.domain.instagram.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class PostInfoDto {


    private long id;
    private String text;
    private String tag;
    private LocalDateTime createdate;
    private User postUploader;
    private long likeCount;
    private boolean likeState;
    private boolean uploader;
    private String postImgUrl;
    private List<Comment> commentList;
}
