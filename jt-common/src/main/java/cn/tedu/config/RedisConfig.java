package cn.tedu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.Jedis;

@Configuration 	//我是一个配置类    一般都会与@Bean联用
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;
    //将返回值的结果交给spring容器进行管理,如果以后想要使用该对象则可以直接注入.
    @Bean
    public Jedis jedis() {
        return new Jedis(host, port);
    }
}
