package jpabook.jpashop.jwt.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignVo {

    String email;
    String password;
    String name;
}
