package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {



    @Autowired
    private final MemberRepository memberRepository;
    //private  final PasswordEncoder passwordEncoder;





    /** 회원가입 **/
    @Transactional
    public  Long join(Member member){
        validateDuplicateMember(member); // 중복회원 체크
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }


    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 한건 조회!!
    public Member findOne(Long memberId){
        return  memberRepository.findById(memberId).get();
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findById(id).get();
        member.setName(name);
    }
}
