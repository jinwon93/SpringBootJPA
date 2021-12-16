package jpabook.jpashop.dto.instagram.post;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PostUploadDto {

    private String text;
    private String tag;
}
