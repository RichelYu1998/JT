package cn.tedu.aop;

import org.aspectj.lang.JoinPoint;
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
    /*
    * 2.定义通知方法
    * 需求
    *   1.获取目标方法名称
    *   2.获取目标方法对象
    *   3.获取用户传输对象
    * */
    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        System.out.println("我是前置通知");
        //1.获取方法名称
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        //2.获取对象
        Object target = joinPoint.getTarget();
        //3.获取参数
        Object[] objs = joinPoint.getArgs();
        System.out.println("类名名称："+className);
        System.out.println("方法名称："+methodName);
        System.out.println("对象名称："+target);
        System.out.println("方法参数："+objs);
    }
}
