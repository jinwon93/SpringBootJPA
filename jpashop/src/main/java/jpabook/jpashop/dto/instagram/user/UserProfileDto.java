package jpabook.jpashop.dto.instagram.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jpabook.jpashop.domain.instagram.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

    private boolean loginUser;
    private boolean follow;
    private User user;
    private int postCount;
    private int userFollowerCount;
    private int userFollowingCount;
}
