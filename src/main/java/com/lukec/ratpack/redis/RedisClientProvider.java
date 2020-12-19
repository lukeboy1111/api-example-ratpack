package com.lukec.ratpack.redis;

import java.util.Arrays;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Provider;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;
 
/**
 * @author xiaofeng
 * @version V1.0
 * @title: RedisClientProvider
 * @package: com.example.provider
 * @description: TODO
 * @date 2018/6/25 17:23
 */
public class RedisClientProvider implements Provider<RedisAsyncCommands> {
    Logger logger = LoggerFactory.getLogger(getClass());
    private Integer database = 0;
    private String host = "localhost";
    private Integer port=6379;
    private String password="";
    

 
    private static ShardedJedisPool sharedPool;
 
    private ShardedJedisPool getJedisPool() {
        if (Objects.isNull(sharedPool)) {
            JedisPoolConfig config = new JedisPoolConfig();
            JedisShardInfo info = new JedisShardInfo(this.host, this.port.intValue());
            if (this.password != null && !this.password.isEmpty()) {
                info.setPassword(password);
            }
            sharedPool = new ShardedJedisPool(config, Arrays.asList(new JedisShardInfo[]{info}));
        } else {
            logger.error("initialze operate error,please waitting.....");
            return sharedPool;
        }
        return sharedPool;
    }
 
    @Override
    public RedisAsyncCommands get() {
        return null;
    }

    public ShardedJedisPool getClient() {
	return getJedisPool();
    }
}
 