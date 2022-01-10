package jpabook.jpashop.controller.instagram;


import jpabook.jpashop.core.auth.PrincipalDetails;
import jpabook.jpashop.core.handler.CustomApiException;
import jpabook.jpashop.dto.instagram.post.PostDto;
import jpabook.jpashop.dto.instagram.post.PostUploadDto;
import jpabook.jpashop.service.instagram.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    //post 업로드 화면으로 이동
    @GetMapping("/post/upload")
    public String upload(){
        return "post/upload";
    }

    //post 업로드 후 프로필 화면으로 이동
    @PostMapping("post")
    public String uploadPost(PostUploadDto postUploadDto , @RequestParam("uploadImgUrl")MultipartFile multipartFile,
                             RedirectAttributes redirectAttributes , @AuthenticationPrincipal PrincipalDetails principalDetails){

        if (multipartFile.isEmpty()){
            throw  new CustomApiException("이미지가 첨부되지 않았습니다");
        }
        postService.save(postUploadDto , multipartFile , principalDetails);
        redirectAttributes.addAttribute("id" , principalDetails.getUser().getId());
        return "redirect:/user/profile";
    }

    //post 수정 화면으로 이동
    @GetMapping("/post/update/{postId}")
    public String update(@PathVariable long postId , Model model){

        PostDto postDto = postService.getPostDto(postId);
        model.addAttribute("postDto" , postDto);
        return "post/update";
    }

    //포스트 수정 폼
    @PostMapping("/post/update")
    public String postUpdate(PostUploadDto postUploadDto , @AuthenticationPrincipal PrincipalDetails principalDetails , RedirectAttributes redirectAttributes){
        postService.update(postUploadDto);
        redirectAttributes.addAttribute("io" ,principalDetails.getUser().getId());
        return "redirect:/user/profile";
    }


    //포스트 삭제 폼
    @PostMapping("/post/delete")
    public String delete(@RequestParam("postId") long postId , @AuthenticationPrincipal PrincipalDetails principalDetails , RedirectAttributes redirectAttributes){
        postService.delete(postId);
        redirectAttributes.addAttribute("id" , principalDetails.getUser().getId());
        return "redirect:/user/profile";
    }

    //검색 페이지 - 게시글의 태그 눌러서 이동
    @GetMapping("/post/searchForm")
    public String search(@RequestParam("tag") , String tag , Model model){
        model.addAttribute("tag" , tag);
        return "post/search";
    }

    //검색 폼 입력 후 페이지 이동
    @PostMapping("/post/searchForm")
    public String searchForm(String tag  , RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("tag" , tag);
        return "redirect:/post/search";
    }

    //좋아요한 포스트 출력 페이지로 이동
    @GetMapping("/post/likes")
    public String likes(){
        return "post/likes";
    }

    //인기 포스트 페이지로 이동
    @GetMapping("/post/popular")
    public String popular(){
        return "post/popular";
    }
}
