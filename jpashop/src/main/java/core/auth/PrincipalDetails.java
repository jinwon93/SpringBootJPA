package core.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import secondpage.domain.User;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
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

//    @Override
//    public Map<String , Object> getAtttributes(){
//        return  atttributes;
//    }

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

//    @Override
//    public  String getName(){
//        return  (String) atttributes.get("name");
//    }
}
