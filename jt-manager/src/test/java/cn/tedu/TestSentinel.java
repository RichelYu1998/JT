package cn.tedu;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class TestSentinel { //主要完成哨兵测试

    /**
     * 参数说明:
     *   masterName: 主机名称
     *   sentinels:  哨兵节点信息.
     */
    @Test
    public void test01(){
        Set<String> sentinels = new HashSet<>();
        String node = "192.168.126.129:26379";
        sentinels.add(node);
        JedisSentinelPool sentinelPool =
                new JedisSentinelPool("mymaster", sentinels);
        Jedis jedis = sentinelPool.getResource(); //获取资源
        jedis.set("sentinel", "redis哨兵机制配置成功!!!!");
        System.out.println(jedis.get("sentinel"));
    }

}