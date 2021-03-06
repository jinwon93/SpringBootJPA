package jpabook.jpashop.service.instagram;


import jpabook.jpashop.core.auth.PrincipalDetails;
import jpabook.jpashop.domain.instagram.Post;
import jpabook.jpashop.domain.instagram.User;
import jpabook.jpashop.dto.instagram.post.PostDto;
import jpabook.jpashop.dto.instagram.post.PostInfoDto;
import jpabook.jpashop.dto.instagram.post.PostPreviewDto;
import jpabook.jpashop.dto.instagram.post.PostUploadDto;
import jpabook.jpashop.repository.instagram.CommentRespository;
import jpabook.jpashop.repository.instagram.LikeRepository;
import jpabook.jpashop.repository.instagram.PostRepository;
import jpabook.jpashop.repository.instagram.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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

        //????????? ?????? ????????? ????????? ???????????? likesCount, likesState, CommentList??? ???????????????.
        postInfoDto.setLikeCount(post.getLikesList().size());
        post.getLikesList().forEach(likes -> {
            if(likes.getUser().getId() == sessionId) postInfoDto.setLikeState(true);
        });
        postInfoDto.setCommentList(post.getCommentList());

        //????????? ????????? ????????? ????????????.
        User user = userRepository.findById(post.getUser().getId()).get();

        postInfoDto.setPostUploader(user);
        if(sessionId == post.getUser().getId()) postInfoDto.setUploader(true);
        else postInfoDto.setUploader(false);

        return postInfoDto;
    }

    @Transactional
    public PostDto getPostDto(long postId) {
        //?????? ?????? ?????? -> post??? ???????????? ?????? ????????? ?????? ???????????? ???????????? ??????????????? ???????????
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


        //????????? likes??? ?????? ?????? ????????? ??????
        likeRepository.deleteLikesByPost(post);


        //????????? Comment??? ?????? ?????? ????????? ??????
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


    @Transactional
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


    public Page<PostPreviewDto> getLikesPost(long sessionId , Pageable pageable){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT p.id p.post_img_url , COUNT(p.id) as likesCunt");
        sb.append("FROM likes l, post p ");
        sb.append("WHERE l.post_id = p.id ");
        sb.append("AND p.id IN (SELECT p.id FROM likes l, post p WHERE l.user_id = ? AND p.id = l.post_id) ");
        sb.append("GROUP BY p.id ");
        sb.append("ORDER BY p.id");


        //?????? ?????? --> querydsl??? ???????????? ??????
        Query query  = em.createNativeQuery(sb.toString())
                .setParameter(1, sessionId);


        JpaResultMapper result = new JpaResultMapper();
        List<PostPreviewDto> postLikesList = result.list(query , PostPreviewDto.class);


        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > postLikesList.size() ? postLikesList.size() : (start + pageable.getPageSize());

        if (start > postLikesList.size()) return new PageImpl<PostPreviewDto>(postLikesList.subList(0 , 0) , pageable , 0);

        Page<PostPreviewDto>  postLikesPage = new PageImpl<>(postLikesList.subList(start ,  end) , pageable , postLikesList.size());
        return postLikesPage;

    }


    public List<PostPreviewDto> getPopularPost(){

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT p.id, p.post_img_url, COUNT(p.id) as likesCount ");
        sb.append("FROM likes l, post p ");
        sb.append("WHERE l.post_id = p.id ");
        sb.append("AND p.id IN (SELECT p.id FROM likes l, post p WHERE p.id = l.post_id) ");
        sb.append("GROUP BY p.id ");
        sb.append("ORDER BY likesCount DESC, p.id ");
        sb.append("LIMIT 12 ");

        Query query = em.createNativeQuery(sb.toString());

        JpaResultMapper result = new JpaResultMapper();
        List<PostPreviewDto> postPreviewDtoList = result.list(query , PostPreviewDto.class);

        return postPreviewDtoList;
    }
}
