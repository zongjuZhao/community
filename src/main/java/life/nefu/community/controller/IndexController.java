package life.nefu.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author juran
 * @create 2021-03-19 10:22
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){return "index";}
}
