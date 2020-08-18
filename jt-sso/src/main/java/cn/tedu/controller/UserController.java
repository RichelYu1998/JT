package cn.tedu.controller;

import cn.tedu.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//返回参数为json
@RestController
public class UserController {
    @Resource
    private UserService userService;
    /*
    * demo 测试
    * */
    @RequestMapping("/getMsg")
    public String getMsg(){
        return "hello";
    }
}
