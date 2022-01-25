package jpabook.jpashop.core.oauth;

import jpabook.jpashop.domain.instagram.User;
import jpabook.jpashop.repository.instagram.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class Oauth2DetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;


    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        OAuth2User oAuth2User = super.loadUser(userRequest); //유저 정보를 가져온다

        Map <String , Object> user_map = oAuth2User.getAttributes();
        String email = (String) user_map.get("email");
        String name = (String) user_map.get("name");
        String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString()); //랜덤한 비밀번호 생성

        User check_user = userRepository.findSUerByEmail(email);

        return null;
    }
}
