package cn.tedu.controller;

import cn.tedu.service.UserService;
import cn.tedu.vo.SysResult;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.swing.plaf.PanelUI;

//返回参数为json
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    /*
     * demo 测试
     * */
    @RequestMapping("/getMsg")
    public String getMsg() {
        return "hello";
    }

    /**
     * 1.url请求地址: http://sso.jt.com/user/check/{param}/{type}
     * 2.请求参数:    {需要校验的数据}/{校验的类型是谁}
     * 3.返回值结果:  SysResult返回  需要包含true/false
     * 4.JSONP请求方式 :返回值必须由特殊的格式封装 callback(json)
     */
    @RequestMapping("/check/{param}/{type}")
    public JSONPObject checkUser(@PathVariable String param, @PathVariable Integer type, String callback){

        //1.校验数据库中是否存在该数据
        boolean flag = userService.checkUser(param,type);  //存在true  不存在false
        return new JSONPObject(callback, SysResult.success(flag));
    }
}
