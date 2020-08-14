package cn.tedu.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//注解在方法中使用
@Retention(RetentionPolicy.RUNTIME)//运行时有效
public @interface CacheFind {
    String key();               //1.设定key 用户自己设定
    int seconds() default 0;    //指定超时时间
}
