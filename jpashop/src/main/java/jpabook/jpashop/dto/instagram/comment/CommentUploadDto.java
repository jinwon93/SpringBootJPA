package jpabook.jpashop.dto.instagram.comment;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentUploadDto {

    @NotBlank
    private String text;

    @NotBlank
    private Long postId;
}
