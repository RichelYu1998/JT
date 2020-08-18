package cn.tedu.controller;

import cn.tedu.pojo.User;
import cn.tedu.service.HttpClientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HttpClientController {
    @Resource
    private HttpClientService httpClientService;

    /**
     * 用户测试路径:
     * http://www.jt.com/user/httpClient/saveUser/admin123456/123456789
     * 将数据传递给sso.jt.com
     * return "用户请求成功"
     */
    @RequestMapping("/user/httpClient/saveUser/{username}/{password}")
    public String saveUser(User user){ //参数接收与对象的属性必须保持一致.可以自动赋值springmvc

        httpClientService.saveUser(user);
        return "httpClient测试成功!!!";
    }

}
