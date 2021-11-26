package secondpage.repository;

import secondpage.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    int countByUsername(String username);

    int countByEmail(String email);

    User findAllByUsername(String username);

    Optional<User> findByEmail(String email);
}
