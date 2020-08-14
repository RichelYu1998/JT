package cn.tedu.aop;

import cn.tedu.anno.CacheFind;
import cn.tedu.util.ObjectMapperUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

@Component//我是一个javaBean
@Aspect    //我是一个切面
public class CacheAOP {
    @Resource
    private Jedis jedis;
    @SuppressWarnings("unchecked")
    @Around("@annotation(cacheFind)")
    public Object around(ProceedingJoinPoint joinPoint,CacheFind cacheFind) {

        //1.获取用户注解中的key     ITEM_CAT_LIST::0
        String key = cacheFind.key();
        //2.动态获取第一个参数当做key
        String firstArg = joinPoint.getArgs()[0].toString();
        key += "::"+firstArg;

        Object result = null;
        //3.根据key查询redis.
        if(jedis.exists(key)) {

            //根据redis获取数据信息
            String json = jedis.get(key);
            //如何获取返回值类型
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            result = ObjectMapperUtil.toObject(json, methodSignature.getReturnType());
            System.out.println("aop查询redis缓存");
        }else {
            //如果key不存在,则证明是第一次查询.  应该查询数据库
            try {
                result = joinPoint.proceed(); //目标方法返回值
                System.out.println("AOP查询数据库获取返回值结果");
                //将数据保存到redis中
                String json = ObjectMapperUtil.toJSON(result);
                int seconds = cacheFind.seconds();
                if(seconds>0)
                    jedis.setex(key, seconds, json);
                else
                    jedis.set(key, json);

            } catch (Throwable e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return result;
    }
   /* //1.定义切入点表达式
    @Pointcut("bean(itemCatServiceImpl)")//只拦截xxx类中的方法
    public void pointcut(){

    }
    *//*
    * 2.定义通知方法
    * 需求
    *   1.获取目标方法名称
    *   2.获取目标方法对象
    *   3.获取用户传输对象
    * *//*
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
    }*/
}
