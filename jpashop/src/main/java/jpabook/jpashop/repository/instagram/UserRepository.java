package jpabook.jpashop.repository.instagram;

import jpabook.jpashop.domain.instagram.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    //https://moonsbeen.tistory.com/281?category=1213906


    User findUerByEmail(String email);

    User findUserById(Long id);
}
