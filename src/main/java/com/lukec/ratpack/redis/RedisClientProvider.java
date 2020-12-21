package com.lukec.ratpack.redis;

import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Provider;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
 
public class RedisClientProvider implements Provider<ShardedJedis> {
    final static Logger logger = LoggerFactory.getLogger(RedisClientProvider.class);
    private String host = "localhost";
    private Integer port=6379;
    private String password="";
    
    private static ShardedJedisPool sharedPool;
 
    private ShardedJedisPool getJedisPool() {
        if (Objects.isNull(sharedPool)) {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setMaxTotal(128);
            poolConfig.setMaxIdle(128);
            poolConfig.setMinIdle(16);
            poolConfig.setTestOnBorrow(true);
            poolConfig.setTestOnReturn(true);
            poolConfig.setTestWhileIdle(true);
            poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
            poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
            poolConfig.setNumTestsPerEvictionRun(3);
            poolConfig.setBlockWhenExhausted(true);
            JedisShardInfo info = new JedisShardInfo(this.host, this.port.intValue());
            if (this.password != null && !this.password.isEmpty()) {
                info.setPassword(password);
            }
            sharedPool = new ShardedJedisPool(poolConfig, Arrays.asList(new JedisShardInfo[]{info}));
        } 
        return sharedPool;
    }
 
    @Override
    public ShardedJedis get() {
        return getJedisPool().getResource();
    }
    
    public void closePool() {
	getJedisPool().getResource().close();
    }
}
 