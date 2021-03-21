package life.nefu.community.advice;

import life.nefu.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 第2种异常 CustomizeExceptionHandler：处理内部上下文异常：比如查数据库，没有找到id：路由后内部错误
 * @author juran
 * @create 2021-03-21 19:31
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model) {
//      QuestionService 抛出的 异常
        if (e instanceof CustomizeException){

        model.addAttribute("message", e.getMessage());
        }else {
        model.addAttribute("message", "服务器冒烟，稍后再试试");
        }
        return new ModelAndView("error");
    }

}
