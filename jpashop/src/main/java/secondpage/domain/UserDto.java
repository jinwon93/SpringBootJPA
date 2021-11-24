package secondpage.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user_dto")
@Entity
@Getter
public class UserDto {

    @Id
    @GeneratedValue
    private Long Id;


    private String email;
    private String password;
    private String username;

}