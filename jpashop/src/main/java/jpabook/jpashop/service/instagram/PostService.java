package jpabook.jpashop.service.instagram;


import jpabook.jpashop.core.auth.PrincipalDetails;
import jpabook.jpashop.domain.instagram.Post;
import jpabook.jpashop.domain.instagram.User;
import jpabook.jpashop.dto.instagram.post.PostDto;
import jpabook.jpashop.dto.instagram.post.PostInfoDto;
import jpabook.jpashop.dto.instagram.post.PostUploadDto;
import jpabook.jpashop.repository.instagram.CommentRespository;
import jpabook.jpashop.repository.instagram.LikeRepository;
import jpabook.jpashop.repository.instagram.PostRepository;
import jpabook.jpashop.repository.instagram.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostService {


    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final CommentRespository commentRespository;
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


    @Transactional
    public PostInfoDto getPostInfoDto(long postId, long sessionId) {
        PostInfoDto postInfoDto = new PostInfoDto();
        postInfoDto.setId(postId);

        Post post = postRepository.findById(postId).get();
        postInfoDto.setTag(post.getTag());
        postInfoDto.setText(post.getText());
        postInfoDto.setPostImgUrl(post.getPostImgUrl());
        postInfoDto.getCreateDate(post.getCreateDate());

        //포스트 정보 요청시 포스트 엔티티의 likesCount, likesState, CommentList를 설정해준다.
        postInfoDto.setLikeCount(post.getLikesList().size());
        post.getLikesList().forEach(likes -> {
            if(likes.getUser().getId() == sessionId) postInfoDto.setLikeState(true);
        });
        postInfoDto.setCommentList(post.getCommentList());

        //포스트 주인의 정보를 가져온다.
        User user = userRepository.findById(post.getUser().getId()).get();

        postInfoDto.setPostUploader(user);
        if(sessionId == post.getUser().getId()) postInfoDto.setUploader(true);
        else postInfoDto.setUploader(false);

        return postInfoDto;
    }

    @Transactional
    public PostDto getPostDto(long postId) {
        //예외 처리 필요 -> post의 작성자가 아닌 사람이 해당 페이지에 접근하여 수정하려고 한다면??
        Post post = postRepository.findById(postId).get();

        PostDto postDto = PostDto.builder()
                .id(postId)
                .tag(post.getTag())
                .text(post.getText())
                .postImgUrl(post.getPostImgUrl())
                .build();

        return postDto;
    }


    @Transactional
    public void  update(PostUploadDto postUploadDto){
        Post post = postRepository.findById(postUploadDto.getId()).get();
        post.update(postUploadDto.getTag() , postUploadDto.getText());
    }


    @Transactional
    public void delete(long postId){
        Post post = postRepository.findById(postId).get();


        //관련된 likes의 정보 먼저 삭제해 준다
        likeRepository.deleteLikesByPost(post);


        //관련된 Comment의 정보 먼저 삭제해 준다
        commentRespository.deleteCommentByPost(post);


        File file = new File(uploadUrl + post.getPostImgUrl());
        file.delete();

        postRepository.deleteById(postId);

    }


    @Transactional
    public Page<Post> getPost(long sessionId , Pageable pageable){
        Page<Post> postList = postRepository.mainStory(sessionId , pageable);

        postList.forEach(post -> {
            post.updateLikesCount(post.getLikesList().size());
            post.getLikesList().forEach(likes -> {
                if (likes.getUser().getId() == sessionId) post.updateLikesState(true);
            });
        });
        return postList;
    }


    public Page<Post> getTagPost(String tag ,  long sessionId ,  Pageable pageable){

        Page<Post> postList = postRepository.searchResult(tag , pageable);

        postList.forEach(post -> {
            post.updateLikesCount(post.getLikesList().size());
            post.getLikesList().forEach(likes -> {
                if (likes.getUser().getId() == sessionId) post.updateLikesState(true);
            });
        });

        return postList;
    }
}
