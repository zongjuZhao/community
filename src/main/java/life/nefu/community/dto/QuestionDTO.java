package life.nefu.community.dto;

import life.nefu.community.model.User;
import lombok.Data;

/**
 * @author juran
 * @create 2021-03-20 10:17
 */
@Data
public class QuestionDTO {
    private Integer id ;
    private String title ;
    private String description;
    private Long gmtCreate;
    private Long gmtModified ;
    //  难点1：Question表,通过creator ，查看User表的Id,获取User表的avtor_url-->解决：QuestionDTO
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
//  新增：解决：难点1：
    private User user;
}
