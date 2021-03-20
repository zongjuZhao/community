package life.nefu.community.model;

import lombok.Data;

/**
 * @author juran
 * @create 2021-03-19 18:52
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}
