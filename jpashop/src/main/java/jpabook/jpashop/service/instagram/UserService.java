package jpabook.jpashop.service.instagram;


import jpabook.jpashop.core.auth.PrincipalDetails;
import jpabook.jpashop.core.handler.ex.CustomApiException;
import jpabook.jpashop.core.handler.ex.CustomVaildationException;
import jpabook.jpashop.domain.instagram.User;
import jpabook.jpashop.dto.instagram.user.UserSignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import jpabook.jpashop.dto.instagram.user.UserProfileDto;
import jpabook.jpashop.dto.instagram.user.UserUpdateDto;
import jpabook.jpashop.repository.instagram.FollowRepository;
import jpabook.jpashop.repository.instagram.UserRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FollowRepository followRepository;


    public User save(UserSignupDto userSignupDto){
        if (userRepository.findUerByEmail(userSignupDto.getEmail()) != null) throw new CustomApiException("이미 존재하는 email입니다");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return userRepository.save(User.builder()
                .email(userSignupDto.getEmail())
                .password(userSignupDto.getPassword())
                .phone(userSignupDto.getPhone())
                .name(userSignupDto.getName())
                .title(null)
                .website(null)
                .profileImgUrl(null)
                .build());
    }

    @Value("${profileImg.path}")
    private String uploadFolder;



    @Transactional
    public  void  update(UserUpdateDto userUpdateDto , MultipartFile multipartFile , PrincipalDetails principalDetails){

        User user = userRepository.findById(principalDetails.getUser().getId()).orElseThrow(() ->{
            return new CustomVaildationException("찾을 수 없는 user입니다.");
        });
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(!multipartFile.isEmpty()) { //파일이 업로드 되었는지 확인
            String imageFileName = user.getId() + "_" + multipartFile.getOriginalFilename();
            Path imageFilePath = Paths.get(uploadFolder + imageFileName);
            try {
                if (user.getProfileImgUrl() != null) { // 이미 프로필 사진이 있을경우
                    File file = new File(uploadFolder + user.getProfileImgUrl());
                    file.delete(); // 원래파일 삭제
                }
                Files.write(imageFilePath, multipartFile.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            user.updateProfileImgUrl(imageFileName);
        }

        user.updata(
                encoder.encode(userUpdateDto.getPassword()),
                userUpdateDto.getPhone(),
                userUpdateDto.getName(),
                userUpdateDto.getTitle(),
                userUpdateDto.getWebsite()
        );

        principalDetails.updateUser(user);
    }

    @Transactional
    public UserProfileDto getUserProfileDto(long profileId , long sessionId){

        UserProfileDto userProfileDto = new UserProfileDto();
        User user = userRepository.findById(profileId).orElseThrow(() -> {
            return  new CustomVaildationException(("찾을 수 없는 user입니다"));
        });
        userProfileDto.setUser(user);
        userProfileDto.setPostCount(user.getPostList().size());

        //loginEmail을 활용하여 currentId.가 로그인한 사용자 인지 확인
        User loginUser = userRepository.findById(sessionId).orElseThrow(() -> {
            return  new CustomVaildationException("찾을 수 없는 user입니다");
        });
        userProfileDto.setLoginUser(loginUser.getId() == user.getId());

        // currentId를 가진 user가 loginEmail을 가진 user를 구독 했는지 확인
        userProfileDto.setFollow(followRepository.findFollowByFromUserAndToUserId(loginUser.getId() , user.getId()) != null);


        //userProfileDto.setFollow();
        // currentId를 가진 user의 팔로워 수를 확인한다.
        userProfileDto.setUserFollowerCount(followRepository.findFollowerCountById(profileId));
        userProfileDto.setUserFollowingCount(followRepository.findFollowerCountById(profileId));

        //좋아요 수 확인
        user.getPostList().forEach(post -> {
            post.updateLikesCount(post.getLikesList().size());
        });


        return  userProfileDto;
    }
}
