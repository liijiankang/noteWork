package com.ljk.myTest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxWaitMillis(1000);
        jedisPoolConfig.setTestOnReturn(true);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"192.168.146.100",6379);
        Jedis jedis = jedisPool.getResource();
        String key1Value = jedis.get("key1");
        System.out.println(key1Value);

    }
}
