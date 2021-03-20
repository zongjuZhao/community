package life.nefu.community.controller;

import life.nefu.community.dto.PaginationDTO;
import life.nefu.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author juran
 * @create 2021-03-19 10:22
 */
@Controller
public class IndexController {
//  写获取列表的方法
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name="page",defaultValue = "1")Integer page,
                        @RequestParam(name="size",defaultValue = "5")Integer size
                        ) {

//      完成首页问题列表功能:进入首页，就开始查询，刷新页面
        PaginationDTO pagination=questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        //如何在前端：获取到model? 模板循环thymleaf:foreach

        return "index";
    }
}
