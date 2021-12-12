package secondpage.service;


import core.auth.PrincipalDetails;
import core.handler.CustomVaildationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import secondpage.domain.user.User;
import secondpage.dto.user.UserLoginDto;
import secondpage.dto.user.UserProfileDto;
import secondpage.dto.user.UserUpdateDto;
import secondpage.repository.FollowRepository;
import secondpage.repository.UserRepository;
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


    @Transactional
    public void save(UserLoginDto userLoginDto){


        if (userRepository.findSUerByEmail(userLoginDto.getEmail()) != null){
            new CustomVaildationException("이미 존자하는 회원입니다");
        }
        userRepository.save(User.builder()
                .email(userLoginDto.getEmail())
                .password(userLoginDto.getPassword())
                .phone(userLoginDto.getPhone())
                .name(userLoginDto.getName())
                .build()
                );
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




        return  userProfileDto;
    }
}
