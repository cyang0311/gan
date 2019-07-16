package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
    @RequestMapping("{page}")
    public String main(@PathVariable String page){

        return "WEB-INF/pages/"+page;
//        return "redirect:/login.jsp";
    }
}
