import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.FileWriter;
import java.io.IOException;

public class JedisTest {
    @Test
    public void jedisKeyTest() throws IOException {
        Jedis jedis = new Jedis("192.168.3.15", 6379);
        FileWriter fileWriter = new FileWriter("/Users/apple/IdeaProjects/redis/ch01-redis/log.txt" + "======================");
        fileWriter.write(jedis.info() + '\n');
        fileWriter.write(jedis.ping() + "\n" + jedis.keys("*") + "\n" + jedis.exists("k1") + "\n"+jedis.type("k2"));
        jedis.expire("k1", 10);
        jedis.lpush("list01", "v1", "v2", "v3", "v4", "v5");
        jedis.rpush("list02", "v1", "v2", "v3", "v4", "v5");
        
        fileWriter.flush();
        fileWriter.close();

    }
}
