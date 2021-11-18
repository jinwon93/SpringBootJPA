package core.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtTokenUtil  implements Serializable {

    private static final long serialVersionUID = -798416586417070603L;
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    /**
     * jwt 토큰에서 username 검색
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token){
        try{
            return getClaimFromToken(token, Claims::getSubject);
        }catch(Exception ex){
            //throw new UsernameFromTokenException("username from token exception");
        }
    }
    /**
     * jwt 토큰에서 날짜 만료 검색
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * secret 키를 가지고 토큰에서 정보 검색
     * @param token
     * @return
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


}
