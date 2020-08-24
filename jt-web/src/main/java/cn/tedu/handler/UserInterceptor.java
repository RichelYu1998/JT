package cn.tedu.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//拦截器的类(业务) 拦截器的配置文件(拦截什么配置请求)
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Resource
    private JedisCluster jedisCluster;
    /*
     * 实现pre方法
     * 返回值说明：
     *           return false 表示拦截 需要配合重定向一起使用
     *           return true  表示放行
     * 需求1：如果用户没有登录，则重定向到系统登录页面
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //业务实现  1.获取cookie
        Cookie[] cookies = request.getCookies();
        if(cookies !=null && cookies.length>0) {
            for (Cookie cookie : cookies) {
                if("JT_TICKET".equals(cookie.getName())){
                    //如果equals则说明cookie是存在的.
                    String ticket = cookie.getValue();
                    //2.redis中是否有该记录  如果有记录则放行请求.
                    if(jedisCluster.exists(ticket)) {
                        //说明数据以及存在.可以放行
                        return true;
                    }
                }
            }
        }
        //重定向到用户登录页面
        response.sendRedirect("/user/login.html");
        return false;
    }
}
