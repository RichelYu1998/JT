package cn.tedu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//我是一个配置类
public class CorsConfig implements WebMvcConfigurer {
    //拓展跨域请求的方法
    @Override
    public void addCorsMappings(CorsRegistry registry){
        //1.允许什么样的请求进行跨域
        // /-->允许一级目录进行请求 /**-->多级目录请求
        registry.addMapping("/**")
        //2.允许哪些服务器进行跨域
            .allowedOrigins("http://www.jt.com")
        //3.允许的请求方式
            .allowedMethods("GET","POST","PUT","DELETE","HEAD")
        //4.是否允许携带cookie
            .allowCredentials(true)
        //5.允许跨域的持续时间
            .maxAge(1800);
    }
}
