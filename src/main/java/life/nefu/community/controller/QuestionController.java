package life.nefu.community.controller;

import life.nefu.community.dto.QuestionDTO;
import life.nefu.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author juran
 * @create 2021-03-20 22:29
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id")Integer id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
