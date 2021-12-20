package jpabook.jpashop.dto.instagram.post;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUploadDto {


    private long id;
    private String text;
    private String tag;
}
