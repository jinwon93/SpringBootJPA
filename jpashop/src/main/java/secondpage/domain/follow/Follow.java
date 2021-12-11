package secondpage.domain.follow;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import secondpage.domain.user.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Follow {


    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "from_user_id")
    @ManyToOne
    private User fromUser;

    @JoinColumn(name = "to_user_id")
    @ManyToOne
    private User toUser;


    @Builder
    public Follow(User fromUser, User toUser) {
        this.fromUser = fromUser;
        this.toUser = toUser;
    }
}
