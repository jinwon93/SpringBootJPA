package secondpage.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50 , nullable = false , unique = true)
    private String username; // 사용자 아이디

    @JsonIgnore
    @Column(length = 200 , nullable = false)
    private String password;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String website;

    @Column(length = 50)
    private String bio;

    @Column(length = 50 , nullable = false , unique = true)
    private String email;

    @Column(length = 32)
    private String phone;

    @Column(length = 16)
    private String gender;

    @Column(name = "profile_image")
    private String provider;

    @Column(name = "provider_id",length = 50)
    private String providerId;


    private int active = 1;

    @Column(length = 32)
    private String roles;

    @CreationTimestamp
    @Column(name = "create_date")
    private Timestamp createDate;


    @CreationTimestamp
    @Column(name = "update_date")
    private Timestamp updateeDate;


    public User(String username, String password,  String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active =1;
    }


}
