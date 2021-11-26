package secondpage.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Setter
@Getter
public class UserDto {




    private String email;
    private String password;
    private String auth;

}