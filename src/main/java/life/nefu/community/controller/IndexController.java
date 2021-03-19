package life.nefu.community.controller;

import life.nefu.community.mapper.UserMapper;
import life.nefu.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author juran
 * @create 2021-03-19 10:22
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("token")){
                String token = cookie.getValue();
                //      希望传过去token,从数据库中获取user对象
                User user=userMapper.findByToken(token);
                if (user!=null){
                    //页面展示
                    request.getSession().setAttribute("user", user);
                }
//              拿到token,break
                break;
            }
        }

        return "index";
    }
}
