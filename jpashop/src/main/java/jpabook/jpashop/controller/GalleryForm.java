package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GalleryForm {


    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/gallery")
    public  String createForm(Model model){
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();


        model.addAttribute("members" , members);
        model.addAttribute("items",  items);

        return "gallery/gallery";
    }
}
