package cn.tedu.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//拦截器的类(业务) 拦截器的配置文件(拦截什么配置请求)
@Component
public class UserInterceptor implements HandlerInterceptor {
    /*
    * 实现pre方法
    * 返回值说明：
    *           return false 表示拦截 需要配合重定向一起使用
    *           return true  表示放行
    * 需求1：如果用户没有登录，则重定向到系统登录页面
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.sendRedirect("/user/login.html");
        return false;
    }
}
