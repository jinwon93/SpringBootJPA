package core.auth;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import secondpage.domain.user.User;

import java.util.*;

@Data
public class PrincipalDetails  implements UserDetails{

    private User user;

    private Map<String , Object> atttributes;

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
    public Map<String , Object> getAtttributes(){
        return  atttributes;
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
        return  (String) atttributes.get("name");
    }
}
