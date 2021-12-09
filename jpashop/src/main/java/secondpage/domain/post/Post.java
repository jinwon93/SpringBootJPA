package secondpage.domain.post;



import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Post {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;


    private String postImgUrl;
    private String tag;
    private String text;


}
