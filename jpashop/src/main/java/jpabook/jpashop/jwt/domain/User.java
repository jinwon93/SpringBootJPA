package jpabook.jpashop.jwt.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Entity(name = "user")
@NoArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    @NotEmpty
    @Email
    @Size(min = 5, max = 255)
    private String email;
    @NotEmpty @Size(min = 10, max = 100)
    private String password;
    @NotEmpty @Size(min = 2, max = 100)
    private String name;

    public void setId(Long userId) {
        this.userId = userId;
    }

    @Builder
    public User(Long userId, String email, String password,String name){
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
