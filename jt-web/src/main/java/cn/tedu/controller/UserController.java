package cn.tedu.controller;

import cn.tedu.pojo.User;
import cn.tedu.service.DubboUserService;
import cn.tedu.vo.SysResult;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    @Reference(check = false)
    private DubboUserService dubboUserService;
    /*
    * 业务说明：用一个方法实现页面的通用跳转
    * http://www.jt.com/user/login.html 跳转页面 login.jsp
    * http://www.jt.com/user/register.html 跳转页面 register.jsp
    * */
    @RequestMapping("/{page}")
    public String page(@PathVariable String page){
        return page;
    }
    /**
     * 完成用户注册
     * 1.url地址：  http://www.jt.com/user/doRegister
     * 2.参数：         用户名/密码/电话号码
     * 3.返回值：    SysResult对象
     */
    @RequestMapping("/doRegister")
    @ResponseBody
    public SysResult doRegister(User user){
        dubboUserService.saveUser(user);
        return SysResult.success();
    }
}
