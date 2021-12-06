package secondpage.repository;

import secondpage.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    //https://moonsbeen.tistory.com/281?category=1213906


    User findSUerByEmail(String email);

    User findUserById(Long id);
}
