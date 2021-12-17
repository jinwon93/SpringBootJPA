package jpabook.jpashop.service.instagram;


import jpabook.jpashop.core.auth.PrincipalDetails;
import jpabook.jpashop.domain.instagram.Post;
import jpabook.jpashop.dto.instagram.post.PostInfoDto;
import jpabook.jpashop.dto.instagram.post.PostUploadDto;
import jpabook.jpashop.repository.instagram.PostRepository;
import jpabook.jpashop.repository.instagram.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostService {


    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private EntityManager em;

    private String uploadUrl;


    @Transactional
    public void save(PostUploadDto postUploadDto , MultipartFile multipartFile , PrincipalDetails principalDetails){
        UUID uuid = UUID.randomUUID();
        String imgFileName = uuid + "_" + multipartFile.getOriginalFilename();

        Path imageFilePath = Paths.get(uploadUrl + imgFileName);

        try {
            Files.write(imageFilePath , multipartFile.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }

        postRepository.save(Post.builder()
                .postImgUrl(imgFileName)
                .tag(postUploadDto.getTag())
                .text(postUploadDto.getText())
                .user(principalDetails.getUser())
                .likesCount(0)
                .build());
    }


    //@Transactional
    public PostInfoDto(){
        return;
    }
}
