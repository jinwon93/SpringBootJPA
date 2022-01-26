package jpabook.jpashop.dto.instagram.user;


import lombok.*;
import org.hibernate.validator.constraints.pl.NIP;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
@Builder
@Setter
@Getter
public class UserSignupDto {


    @Size(min = 2 , max = 100 , message = "이메일은 2글자 이상 , 100글자 이하로 작성해 주세요")
    @NotBlank(message = "이메일을 입력해 주세요")
    @Email(message = "이메일 형식에 맞지 않습니다")
    private String email;

    @Size(min = 8 , message = "비밀번호는 8자 이상이어야 합니다 ")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$",
             message ="비밀번호는 영문 대,소문자와 숫자 , 특수기호가 적어도 1개 이상의 포함한 8자 이상의 비밀번호여야 합니다." )
    private String password;

    
    @NotBlank(message = "전화번호를 입력해 주세요.")
    @Pattern(regexp = "^[0*9]+$" , message = "전화 번호는 숫자로만 입력해 주세요")
    private String phone;
    
    
    private String name;


}
