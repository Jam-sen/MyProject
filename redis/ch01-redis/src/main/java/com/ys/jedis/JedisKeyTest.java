package com.ys.jedis;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

public class JedisKeyTest {
    public static void main(String []args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("redis");
        String port = resourceBundle.getString("redis.port");
        String host = resourceBundle.getString("redis.host");
        String timeout = resourceBundle.getString("redis.timeout");
        //连接redis
        Jedis jedis = new Jedis(host,Integer.parseInt(port),Integer.parseInt(timeout));
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
