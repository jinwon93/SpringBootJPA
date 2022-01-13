package jpabook.jpashop.controller.instagram;


import jpabook.jpashop.core.auth.PrincipalDetails;
import jpabook.jpashop.dto.instagram.user.UserProfileDto;
import jpabook.jpashop.dto.instagram.user.UserUpdateDto;
import jpabook.jpashop.service.instagram.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jpabook.jpashop.repository.instagram.UserRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@RequiredArgsConstructor
@Controller
public class UserController {
    
    private final UserService userService;
    
    //사용자 프로필 화면으로 이동
    @GetMapping("/user/profile")
    public String profile(Model model  , @RequestParam long id , @AuthenticationPrincipal PrincipalDetails principalDetails){
        UserProfileDto userProfileDto = userService.getUserProfileDto(id, principalDetails.getUser().getId());
        model.addAttribute("userprofileDto" , userProfileDto);
        return "user/profile";
    }
    
    
    //사용자 정보 수정 페이지로 이동
    @GetMapping("/user/update")
    public String update(){
        return "user/update";
    }


    //사용자 정보 업데이트
    @PostMapping("/user/update")
    public String updateUser(@Valid UserUpdateDto userUpdateDto , BindingResult bindingResult , @RequestParam("profileImgUrl")MultipartFile multipartFile,
                             RedirectAttributes redirectAttributes ,  @AuthenticationPrincipal PrincipalDetails principalDetails){
        userService.update(userUpdateDto  ,multipartFile , principalDetails);
        redirectAttributes.addAttribute("id" , principalDetails.getUser().getId());
        return "redirect:/user/profile";
    }


}
