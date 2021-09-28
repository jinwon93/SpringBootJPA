package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public  void 회원가입() throws  Exception{
        Member member = new Member();
        member.setName("kim");

        Long saveId = memberService.join(member);

        em.flush();
        assertEquals(member ,memberService.findOne(saveId));
    }


    @Test
    public void 중복_회원_예외() throws  Exception{

    }
}