package secondpage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import secondpage.domain.follow.Follow;


public interface FollowRepository extends JpaRepository<Follow , Long> {


    Follow findFollowByFromUserAndToUserId(long from_user_id , long to_user_id);

    @Query(value = "SELECT COUNT(*) FROM follow WHERE to_user_id = :profileId", nativeQuery = true)
    int findFollowerCountById(long profileId);


}