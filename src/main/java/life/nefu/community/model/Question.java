package life.nefu.community.model;

import lombok.Data;

import java.util.List;

/**
 * @author juran
 * @create 2021-03-19 23:09
 */
@Data
public class Question {
    private Integer id ;
    private String title ;
    private String description;
    private Long gmtCreate;
    private Long gmtModified ;
//  难点：Question表,通过creator ，查看User表的Id,获取User表的avtor_url-->解决：QuestionDTO
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;


}
