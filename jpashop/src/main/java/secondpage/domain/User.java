package secondpage.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "email", unique = true)
    private String email;

    @JsonIgnore
    @Column(length = 200 , nullable = false)
    private String password;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String website;

    @Column(length = 50)
    private String bio;


    @Column(length = 32)
    private String phone;

    @Column(length = 16)
    private String gender;

    @Column(name = "profile_image")
    private String provider;

    @Column(name = "provider_id",length = 50)
    private String providerId;


    private int active = 1;



    @Column(name = "auth")
    private String auth;


    @Column(length = 32)
    private String roles;

    @CreationTimestamp
    @Column(name = "create_date")
    private Timestamp createDate;


    @CreationTimestamp
    @Column(name = "update_date")
    private Timestamp updateeDate;


    @Builder
    public User(String email, String password,  String roles , String auth) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.active =1;
        this.auth = auth;
    }



    // 사용자의 권한을 콜렉션 형태로 반환
    // 단, 클래스 자료형은 GrantedAuthority를 구현해야함
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    // 사용자의 id를 반환 (unique한 값)
    @Override
    public String getUsername() {
        return email;
    }

    // 사용자의 password를 반환
    @Override
    public String getPassword() {
        return password;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        // 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금되었는지 확인하는 로직
        return true; // true -> 잠금되지 않았음
    }

    // 패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        // 패스워드가 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        // 계정이 사용 가능한지 확인하는 로직
        return true; // true -> 사용 가능
    }

    
}
