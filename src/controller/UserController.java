package controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;

@Controller
public class UserController {
    @Autowired
    private UserService userServiceImpl;

//    @RequestMapping("selByName")
//    @ResponseBody
//    public String selByName(@RequestParam("uname") String name){
//        User user =  userServiceImpl.sel(name);
//        ObjectMapper mapper = new ObjectMapper();
//        String result="cao" ;
//        try {
//            result= mapper.writeValueAsString(user);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        System.out.println(result);
//        return result;
//    }

    @RequestMapping("login")
    public String login(HttpServletRequest req,@RequestParam("phoneNum") String phoneNum,@RequestParam("upwd") String upwd){
        System.out.println(phoneNum+"   "+upwd);
        User user= userServiceImpl.login(phoneNum, upwd);
        if (user!=null){
            req.getSession().setAttribute("user",user);
            System.out.println("有对象");
        }
        return "index";
    }

    @RequestMapping("getcode")
    @ResponseBody
    public String getCode(HttpServletRequest req){
        String phoneNum = req.getParameter("phoneNum");
        System.out.println("请求验证码");
        System.out.println(phoneNum);
        return userServiceImpl.getCode(phoneNum);
    }
    @RequestMapping("reg")
    public String reg(HttpServletRequest req,@RequestParam("uname") String uname,@RequestParam("upwd")String upwd,@RequestParam("phoneNum")String phoneNum,@RequestParam("ucode")String ucode){
        if (userServiceImpl.reg(req,uname,upwd,phoneNum,ucode)){
            return "login";
        }else {
            if (req.getSession().getAttribute("regError")!=null){
                System.out.println(req.getSession().getAttribute("regError"));
            }
            return "reg";
        }
    }

    @RequestMapping(value = "sel",produces = "text/html;charset=utf-8")
    @ResponseBody
    public void sel(@RequestParam("selName") String uname, HttpServletResponse resp){
        System.out.println("controller");
        System.out.println(userServiceImpl.sel(uname));
        System.out.println("controller");

        resp.setContentType("application/json;charset=utf-8");
        try {
            resp.getWriter().print(userServiceImpl.sel(uname));
        } catch (IOException e) {
            System.out.println("凎凎凎");
            e.printStackTrace();
        }
    }
}
