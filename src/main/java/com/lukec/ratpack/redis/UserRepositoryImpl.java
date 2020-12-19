package com.lukec.ratpack.redis;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;

import javax.inject.Inject;

import com.google.gson.Gson;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;
import com.lukec.ratpack.bo.UserBalance;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class UserRepositoryImpl implements UserRepository {
    
    private final RedisClientProvider provider;
    
    //private final RedisAsyncCommands<String, String> commands;

    @Inject
    public UserRepositoryImpl(RedisClientProvider provider) {
	this.provider = provider;
    }
    
    Function<String, String> getKeyForUser = (id) -> "user:" + id + ":";
    
    @Override
    public Optional<UserBalance> getUserBalanceForUser(String token) {
	RedisAsyncCommands<String, String> commands = provider.get();
	ShardedJedisPool client = provider.getClient();
	ShardedJedis resource = client.getResource();
	String balanceText = resource.get(getKeyForUser.apply(token));
	System.err.println("Balance Text was "+balanceText+" for "+token);
	if(null == balanceText) {
	    return(Optional.empty());
	}
	Gson gson = new Gson();
	UserBalance bal= gson.fromJson(balanceText, UserBalance.class);
	return Optional.of(bal);
    }
    
    @Override
    public void makeDefaultBalance(String token) {
	UserBalance bal = new UserBalance("GBP", BigDecimal.valueOf(100.00d));
	setUserBalance(token, bal);
    }


    @Override
    public void setUserBalance(String token, UserBalance balance) {
	RedisAsyncCommands<String, String> commands = provider.get();
	ShardedJedisPool client = provider.getClient();
	ShardedJedis resource = client.getResource();
	Gson gson = new Gson();
	String balanceText = gson.toJson(balance);
	System.err.println("Balance Text is "+balanceText);
	resource.set(getKeyForUser.apply(token), balanceText);
    }

    
    
}
