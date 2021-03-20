package life.nefu.community.controller;

import life.nefu.community.mapper.QuestionMapper;
import life.nefu.community.mapper.UserMapper;
import life.nefu.community.model.Question;
import life.nefu.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author juran
 * @create 2021-03-19 21:52
 */
@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    //  实现发布问题
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model) {

        //      新增：发布问题后，文本框的文字，保留
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
//      新增：校验逻辑
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        //查询逻辑
        User user = null;
        //新增：判空：用于删除cookie(用户退出登录),不报空指针异常
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    //      希望传过去token,从数据库中获取user对象
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        //页面展示
                        request.getSession().setAttribute("user", user);
                    }
//              拿到token,break
                    break;
                }
            }

        //如果用户未登录，不能发布问题,跳转到publish页面
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
//      如果发布问题成功,跳转到首页，刷新页面，重新展示发布内容
        return "redirect:/";
    }
}
