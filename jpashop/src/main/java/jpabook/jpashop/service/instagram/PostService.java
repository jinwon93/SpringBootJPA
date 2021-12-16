package jpabook.jpashop.service.instagram;


import jpabook.jpashop.core.auth.PrincipalDetails;
import jpabook.jpashop.dto.instagram.post.PostUploadDto;
import jpabook.jpashop.repository.instagram.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostService {


    private final UserRepository userRepository;
    private EntityManager em;

    private String uploadUrl;


    @Transactional
    public void save(PostUploadDto postUploadDto , MultipartFile multipartFile , PrincipalDetails principalDetails){
        UUID uuid = UUID.randomUUID();
        String imgFileName = uuid + "_" + multipartFile.getOriginalFilename();
        return;
    }
}
