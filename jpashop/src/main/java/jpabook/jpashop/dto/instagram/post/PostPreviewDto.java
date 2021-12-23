package jpabook.jpashop.dto.instagram.post;


import lombok.*;

import java.math.BigInteger;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostPreviewDto {

    private long id;
    private  String postImgUrl;
    private long likesCount;

    public PostPreviewDto(BigInteger id, String postImgUrl, BigInteger likesCount) {
        this.id = id.longValue();
        this.postImgUrl = postImgUrl;
        this.likesCount = likesCount.longValue();
    }
}
