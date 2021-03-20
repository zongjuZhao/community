package life.nefu.community.controller;

import life.nefu.community.dto.PaginationDTO;
import life.nefu.community.model.User;
import life.nefu.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author juran
 * @create 2021-03-20 17:36
 */
@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;

    //  不同于,IndexController,动态切换路径
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name="action")String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name="page",defaultValue = "1")Integer page,
                          @RequestParam(name="size",defaultValue = "5")Integer size){
//      被SessionInterceptor先拦截，
        User user = (User) request.getSession().getAttribute("user");
//      如果没登陆，直接查看个人信息->先登录，跳转登陆界面index
        if(user==null){
            return "redirect:/";
        }

//      防止空指针
        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination",paginationDTO);
        return "profile";
    }
}
