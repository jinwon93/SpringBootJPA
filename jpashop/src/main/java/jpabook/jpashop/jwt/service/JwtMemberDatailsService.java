package jpabook.jpashop.jwt.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtMemberDatailsService implements UserDetailsService {

}
