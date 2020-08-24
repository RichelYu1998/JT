package cn.tedu.config;

import cn.tedu.handler.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {
    //开启匹配后缀型配置
    @Resource
    private UserInterceptor userInterceptor;
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

        configurer.setUseSuffixPatternMatch(true);
    }
    //添加拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //暂时拦截购物车/订单模块的请求
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/cart/**","/order/**");
    }
}
