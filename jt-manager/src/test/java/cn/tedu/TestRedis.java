package cn.tedu;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class TestRedis {
    /*
     *完成redis入门案例
     * 如果报错
     * 1)检擦linux防火墙
     * 2）检查3项配置
     * 3)重启redis
     * */
    @Test
    public void test01() {
        Jedis jedis = new Jedis("192.168.126.129", 6379);
        //1.向redis中存数据
        jedis.set("2004", "今天下雨了");
        //2.从redis获取数据
        String value = jedis.get("2004");
        System.out.println(value);
    }

    @Test
    public void test02() {
        Jedis jedis = new Jedis("192.168.126.129", 6379);
        //1.是否存在key
        if (jedis.exists("2004")) ;
        //2.如果存在则设定超时时间
        jedis.expire("2004", 100);
        //3.设置线程暂停2s
        try {
            Thread.sleep(2000);
            //4.设置存活时间
            Long time = jedis.ttl("2004");
            System.out.println("还存活："+time);
            //5.撤销删除
            jedis.persist("2004");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
