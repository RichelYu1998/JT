package cn.tedu.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    //1.新增cookie
    public static void addCookie(String name, String value, String path, String domain, int maxAge, HttpServletResponse response){
        //校验自己完成
        Cookie cookie = new Cookie(name,value);
        cookie.setPath(path);
        cookie.setDomain(domain);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    //2.删除Cookie   0  -1 用枚举类型优化一下
    public static void deleteCookie(String name, String path, String domain, HttpServletResponse response){
        //校验自己完成
        Cookie cookie = new Cookie(name,"");
        cookie.setPath(path);
        cookie.setDomain(domain);
        cookie.setMaxAge(0);    //后期维护使用枚举
        response.addCookie(cookie);
    }
    //3.根据Cookie的name属性获取Cookie对象
    public static Cookie getCookieByName(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if(cookies !=null && cookies.length>0){
            for(Cookie cookie : cookies){
                if(name.equalsIgnoreCase(cookie.getName())){
                    return cookie;
                }
            }
        }
        return null;
    }
}
