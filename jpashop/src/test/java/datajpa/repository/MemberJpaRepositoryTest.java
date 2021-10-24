package datajpa.repository;

import datajpa.entity.Member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;




@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberJpaRepositoryTest {



//    @Autowired MemberJpaRepository memberJpaRepository;
//
//
//    @Test
//    public void testMember(){
//        Member member = new Member("memberA");
//        Member savedMember = memberJpaRepository.save(member);
//
//        Member findMember = memberJpaRepository.find(savedMember.getId());
//
//        assertThat(findMember.getId()).isEqualTo(member.getId());
//        assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
//    }

}