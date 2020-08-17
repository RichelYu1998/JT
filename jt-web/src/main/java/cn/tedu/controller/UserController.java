package cn.tedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    /*
    * 业务说明：用一个方法实现页面的通用跳转
    * http://www.jt.com/user/login.html 跳转页面 login.jsp
    * http://www.jt.com/user/register.html 跳转页面 register.jsp
    * */
    @RequestMapping("/{page}")
    public String page(@PathVariable String page){
        return page;
    }
}
