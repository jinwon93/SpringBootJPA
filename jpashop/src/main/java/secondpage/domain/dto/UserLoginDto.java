package secondpage.domain.dto;

import lombok.*;


@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginDto {

    private String email;
    private String password;
    private String phone;
    private String name;


}