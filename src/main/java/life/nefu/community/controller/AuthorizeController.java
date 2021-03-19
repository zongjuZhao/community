package life.nefu.community.controller;

import life.nefu.community.dto.AccessTokenDTO;
import life.nefu.community.dto.GithubUser;
import life.nefu.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author juran
 * @create 2021-03-19 11:03
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private  String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
//  1.得到/callback得到code
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name = "state")String state) {
//   2.accessToek携带code
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
//   3.携带accessToken
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
//   4.返回user信息
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName() );
        return "index";
    }
}
