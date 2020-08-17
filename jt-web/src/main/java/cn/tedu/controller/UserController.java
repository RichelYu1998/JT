package cn.tedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    /*
    * 业务说明：用一个方法实现页面的通用跳转
    * http://www.jt.com/user/login.html 跳转页面 login.jsp
    * http://www.jt.com/user/register.html 跳转页面 register.jsp
    * */
    @RequestMapping("register")
    public String register(){
        return "register";
    }
    @RequestMapping("login")
    public String login(){
        return "login";
    }
}
