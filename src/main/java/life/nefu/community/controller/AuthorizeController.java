package life.nefu.community.controller;

import life.nefu.community.dto.AccessTokenDTO;
import life.nefu.community.dto.GithubUser;
import life.nefu.community.mapper.UserMapper;
import life.nefu.community.model.User;
import life.nefu.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

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

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
//  1.得到/callback得到code
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletRequest request) {
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
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.getName() );
        if(githubUser!=null){
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            //5.登陆成功，写cookie和session
            request.getSession().setAttribute("user",user);
            //5.重定向主页面：注意redirect返回根目录/，不是index
            return "redirect:/";
        }else{
            //登陆失败，重新登陆
            return "redirect:/";
        }
    }
}