package cn.tedu.aop;

import cn.tedu.vo.SysResult;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice   //定义异常处理的通知.  只拦截Controller层抛出的异常. 并且返回值JSON串
public class SystemExceptionAOP {
    /**
     * 如果跨域访问发生了异常 ,则返回值结果必须经过特殊的格式封装才行.
     * 如果是跨域访问形式,全局异常处理 可以正确的返回结果.
     * 思路: 1.判断用户提交参数中是否有callback参数
     * callback(json)
     * * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Object fail(Exception e, HttpServletRequest request){

        //1.获取用户的请求参数
        String callback = request.getParameter("callback");

        //2.判断参数是否有值
        if(StringUtils.isEmpty(callback)){
            //用户请求不是jsonp跨域访问形式
            //打印异常信息
            e.printStackTrace();
            return SysResult.fail();
        }else{
            //jsonp的报错信息.
            e.printStackTrace();
            return new JSONPObject(callback, SysResult.fail());
        }
    }
}
