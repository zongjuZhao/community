package life.nefu.community.controller;

import life.nefu.community.dto.PaginationDTO;
import life.nefu.community.dto.QuestionDTO;
import life.nefu.community.mapper.UserMapper;
import life.nefu.community.model.Question;
import life.nefu.community.model.User;
import life.nefu.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author juran
 * @create 2021-03-19 10:22
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

//  写获取列表的方法
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name="page",defaultValue = "1")Integer page,
                        @RequestParam(name="size",defaultValue = "5")Integer size
                        ) {
        Cookie[] cookies = request.getCookies();
//      新增：判断：删除cookies后，不报空指针异常
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    //      希望传过去token,从数据库中获取user对象
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        //页面展示
                        request.getSession().setAttribute("user", user);
                    }
//              拿到token,break
                    break;
                }
            }

//      完成首页问题列表功能:进入首页，就开始查询，刷新页面
        PaginationDTO pagination=questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        //如何在前端：获取到model? 模板循环thymleaf:foreach

        return "index";
    }
}
