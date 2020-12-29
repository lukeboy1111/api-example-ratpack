package com.lukec.ratpack.redis.repository.impl;

import java.util.Optional;
import java.util.function.Function;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.lukec.ratpack.bo.UserBalance;
import com.lukec.ratpack.redis.RedisClientProvider;
import com.lukec.ratpack.redis.repository.UserRepository;
import com.lukec.ratpack.support.Constants;

import redis.clients.jedis.ShardedJedis;

public class UserRepositoryImpl implements UserRepository {
    final static Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
    private final RedisClientProvider provider;
    
    @Inject
    public UserRepositoryImpl(RedisClientProvider provider) {
	this.provider = provider;
    }
    
    Function<String, String> getKeyForUser = (id) -> "user:" + id + ":";
    
    @Override
    public Optional<UserBalance> getUserBalanceForUser(String token) {
	ShardedJedis resource = provider.get();
	String balanceText = resource.get(getKeyForUser.apply(token));
	logger.debug("Balance Text was "+balanceText+" for "+token);
	if(null == balanceText) {
	    return(Optional.empty());
	}
	Gson gson = new Gson();
	UserBalance bal= gson.fromJson(balanceText, UserBalance.class);
	provider.closePool();
	return Optional.of(bal);
    }
    
    @Override
    public void makeDefaultBalance(String token) {
	UserBalance bal = new UserBalance(Constants.CURRENCY, Constants.INITIAL_BALANCE);
	setUserBalance(token, bal);
    }


    @Override
    public void setUserBalance(String token, UserBalance balance) {
	ShardedJedis resource = provider.get();
	Gson gson = new Gson();
	String balanceText = gson.toJson(balance);
	logger.debug("Balance Text is "+balanceText);
	resource.set(getKeyForUser.apply(token), balanceText);
	provider.closePool();
    }

    
    
}
