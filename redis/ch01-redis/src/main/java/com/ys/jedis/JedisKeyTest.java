package com.ys.jedis;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

public class JedisKeyTest {
    public static void main(String []args) {
        //连接redis
        Jedis jedis = new Jedis("192.168.3.15",6379);
        System.out.println(jedis.ping());
        Set<String> keys = jedis.keys("*");
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key);
            System.out.println(jedis.exists(key));
        }
        jedis.set("k3", "v3");
    }
}
