package jpabook.jpashop.dto.instagram.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Data
@Builder
public class UserUpdateDto {


    @Size(min = 8 , message = "비밀번호는 8자 이상이여야 합니다 ,")
    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String password;

    @NotBlank(message = "전화번호를 입력해 주세요")
    private String phone;


    @Size(min=1, max = 20 , message = "이름은 1글자 이상 30글자 이내로 작성해 주세요")
    @NotBlank(message = "이름을 입력해 주세요")
    private String name;

    private String title;
    private String website;
}
