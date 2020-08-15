package cn.tedu;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.List;

public class TestShards {//改类表示测试redis分片机制
    /**
     * 说明:在Linux中有3台redis.需要通过程序进行动态链接.
     * 实现数据的存储.
     * 思考: 数据保存到了哪台redis中???
     */
    @Test
    public void test01(){
        List<JedisShardInfo> shards = new ArrayList<>();
        shards.add(new JedisShardInfo("192.168.126.129", 6379));
        shards.add(new JedisShardInfo("192.168.126.129", 6380));
        shards.add(new JedisShardInfo("192.168.126.129", 6381));
        //分片的API
        ShardedJedis shardedJedis = new ShardedJedis(shards);
        shardedJedis.set("王者荣耀", "你好我是小菜鸡,坑死你");
        System.out.println(shardedJedis.get("王者荣耀"));
    }
}
