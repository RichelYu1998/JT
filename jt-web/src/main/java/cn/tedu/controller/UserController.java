package cn.tedu.controller;

import cn.tedu.pojo.User;
import cn.tedu.service.DubboUserService;
import cn.tedu.vo.SysResult;
import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/user")
public class UserController {
    private static final String ticket="JT_TICKET";
    @Reference(check = false)
    private DubboUserService dubboUserService;
    @Resource
    private JedisCluster jedisCluster;
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
    /**
     * 1.url地址:http://www.jt.com/user/doLogin?r=0.04360522021726099
     * 2.参数:  {username:_username,password:_password},
     * 3.返回值结果:  SysResult
     *
     * 需求1:  将cookie名称为 "JT_TICKET"数据输出到浏览器中,要求7天超时.
     * 并且实现"jt.com"数据共享
     *
     * Cookie特点:
     *  1.浏览器中只能查看当前网址下的Cookie信息
     *  2.doMain 表示cookie共享的策略
     *    doMain:www.jd.com   当前的Cookie数据只能在当前域名中使用
     *    doMain:.jd.com      当前可以是共享的可以在域名为jd.com结尾的域名中共享.
     *
     *  * * */
    @RequestMapping("/doLogin")
    @ResponseBody	//返回JSON
    public SysResult doLogin(User user,HttpServletResponse response) {

        //1.通过user传递用户名和密码,交给业务层service进行校验,获取ticket信息(校验之后的回执)
        String ticket = dubboUserService.doLogin(user);
        if(StringUtils.isEmpty(ticket)) {
            //证明用户名或密码错误.
            return SysResult.fail();
        }


        //2.准备Cookie实现数据存储.
        Cookie cookie = new Cookie("JT_TICKET", ticket);
        cookie.setDomain("jt.com");
        cookie.setPath("/");
        cookie.setMaxAge(7*24*60*60); //7天超时
        //将cookie保存到客户端中.
        response.addCookie(cookie);

        return SysResult.success();
    }
    /**
     * 完成回显用户名称
     * url地址: http://sso.jt.com/user/query/494fcc9ac98e49bc98baffd6d8fc6802?callback=jsonp1597999688824&_=1597999688872
     * 参数:  ticket信息
     * 返回值: SysResult对象(userJSON)
     * */
    @RequestMapping("/query/{ticket}")
    @ResponseBody
    public JSONPObject  findUserByTicket(@PathVariable String ticket,String callback){

        String userJSON = jedisCluster.get(ticket);
        if(StringUtils.isEmpty(userJSON)){
            //ticket有误.返回错误信息即可
            return new JSONPObject(callback, SysResult.fail());
        }else{
            return new JSONPObject(callback, SysResult.success(userJSON));

        }
    }
    /**
     * 完成用户退出操作
     * 1.url: http://www.jt.com/user/logout.html
     * 2.没有传递参数
     * 3.返回值: string  重定向到系统首页
     * 业务实现思路:
     * 	 0.先获取cookie中的数据 NAME=JT_TICKET
     * 	 1.删除redis中的数据     key-value    key=cookie中的value
     * 	 2.删除cookie记录	   根据cookie名称  设置存活时间即可.
     *
     * 注意事项: request对象中只能传递cookie的name和value.不能传递其他数据参数.
     * 所以如果需要再次操作cookie则最好设定参数,否则可能导致操作失败
     */
    @RequestMapping("/logout")
    public String  logout(HttpServletRequest request,HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if(cookies !=null && cookies.length >0) {
            for (Cookie cookie : cookies) {
                if("JT_TICKET".equalsIgnoreCase(cookie.getName())) {
                    String ticket = cookie.getValue();
                    //1.删除redis数据
                    jedisCluster.del(ticket);
                    //2.删除cookie   立即删除cookie 0  ,  暂时不删,关闭浏览器时删除 -1
                    cookie.setDomain("jt.com");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        //重定向到系统首页
        return "redirect:/";
    }
}
