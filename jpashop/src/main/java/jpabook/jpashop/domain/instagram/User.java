package jpabook.jpashop.domain.instagram;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100 , nullable = false , unique = true)
    private String email;

    @Column(length = 200 , nullable = false)
    private String password;


    private String phone;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String website;

    @Column(length = 50)
    private String title;


    @Column(length = 100)
    private String profileImgUrl;


    @JsonIgnoreProperties({"user"})
    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
    private List<Post> postList;



    @Builder
    public User(String email, String password, String phone, String title , String website , String profileImgUrl , String name) {
        this.email = email;
        this.password = password;
        this.title = title;
        this.website = website;
        this.profileImgUrl = profileImgUrl;
        this.phone = phone;
        this.name = name;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword(){
        return  password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


    public void  updata(String password , String phone , String name , String title , String website){
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.title = title;
        this.website = website;
    }

    public void updateProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }
}
