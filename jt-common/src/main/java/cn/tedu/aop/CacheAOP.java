package cn.tedu.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component//我是一个javaBean
@Aspect    //我是一个切面
public class CacheAOP {
    //1.定义切入点表达式
    @Pointcut("bean(itemCatServiceImpl)")//只拦截xxx类中的方法
    public void pointcut(){

    }
    @Before("pointcut()")
    public void before(){
        System.out.println("我是前置通知");
    }
}
