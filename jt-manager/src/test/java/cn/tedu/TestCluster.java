package cn.tedu;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class TestCluster {
    @Test
    public void test01(){
        //利用set集合封装redis节点信息
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.126.129",7000));
        nodes.add(new HostAndPort("192.168.126.129",7001));
        nodes.add(new HostAndPort("192.168.126.129",7002));
        nodes.add(new HostAndPort("192.168.126.129",7003));
        nodes.add(new HostAndPort("192.168.126.129",7004));
        nodes.add(new HostAndPort("192.168.126.129",7005));
        JedisCluster jedisCluster=new JedisCluster(nodes);
        //操作只操作主机，从机负责备份
        jedisCluster.set("jedisCluster","你好我是集群");
        System.out.println(jedisCluster.get("jedisCluster"));
    }
}
