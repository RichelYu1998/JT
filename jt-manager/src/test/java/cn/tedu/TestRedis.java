package cn.tedu;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Map;

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
    /**
     * 需求说明:
     *  1.redis.set操作,后面的操作会将之前的value覆盖
     *  要求:如果key已经存在,则不允许赋值.
     *  注意环境: 检查redis中是否有改数据.
     *  */
    @Test
    public void test03(){
        Jedis jedis = new Jedis("192.168.126.129", 6379);
        jedis.flushAll();//清空缓存
        //如果key存在，就不进行赋值操作
        jedis.setnx("boss","马化腾");
        jedis.setnx("boss","马云");
        System.out.println(jedis.get("boss"));
    }
    /**
     * 为数据添加超时时间.保证原子性操作
     * 原子性: 一起完成一起回滚
     * 锁机制: 保证原子性   死锁!!!!
     * 小结: setnx 如果key存在则不赋值
     *       setex  保证原子性操作,并且添加超时时间.
     * * */
    @Test
    public void test04(){
        Jedis jedis = new Jedis("192.168.126.129", 6379);
       jedis.setex("aaa",20,"xxx");//满足原子性需求
    }
    /**
     * 需求:
     *  1.要求赋值操作时,如果数据已经存在则不允许赋值.
     *  2.同时要求添加超时时间 并且满足原子性要求.
     *   private static final String XX = "xx";   只有key存在时,才能赋值
     *   private static final String NX = "nx";   只有key不存在时,赋值
     *   private static final String PX = "px";   毫秒
     *   private static final String EX = "ex";   秒
     */
    @Test
    public void test05(){
        Jedis jedis = new Jedis("192.168.126.129", 6379);
        SetParams setParams=new SetParams();
        setParams.nx().ex(20);
        jedis.set("AAA","8888",setParams);
        System.out.println(jedis.get("AAA"));
    }
    /*
    * 测试hash数据类型
    * */
    @Test
    public void testHash(){
        Jedis jedis = new Jedis("192.168.126.129", 6379);
        jedis.hset("person","name","tomcat");
        jedis.hset("person","age","100");
        Map<String, String> map = jedis.hgetAll("person");
        System.out.println(map);
    }
    /*
     * 测试list集合类型
     * */
    @Test
    public void testList(){
        Jedis jedis = new Jedis("192.168.126.129", 6379);
        jedis.lpush("list","1","2","3","4","5");
        String value = jedis.rpop("list");
        System.out.println(value);
    }
}
