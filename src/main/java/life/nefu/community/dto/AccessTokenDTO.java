package life.nefu.community.dto;

import lombok.Data;

/**
 * @author juran
 * @create 2021-03-19 11:16
 */
@Data
public class AccessTokenDTO {

    private String client_id;
    private String  client_secret;
    private String code;
    private String redirect_uri;
    private String state;



}
