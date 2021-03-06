package jpabook.jpashop.core.auth;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jpabook.jpashop.domain.instagram.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

@Data
public class PrincipalDetails  implements UserDetails , OAuth2User {

    private User user;
    private Map<String , Object> attributes;

    public PrincipalDetails(User user) {
        this.user = user;
    }


    public PrincipalDetails(User user, Map<String ,Object> atttributes){
        this.user = user;
    }


    public void  updateUser(User user){
        this.user =user;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

    @Override
    public  String getName(){
        return  (String) attributes.get("name");
    }
}
