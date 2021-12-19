package jpabook.jpashop.dto.instagram.post;


import lombok.*;
import org.springframework.stereotype.Service;

@Builder
@AllArgsConstructor
@Setter
@Getter
@Data
public class PostDto {


    private long id;
    private String tag;
    private String text;
    private String postImgUrl;
}
