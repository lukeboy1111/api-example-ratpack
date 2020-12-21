package com.lukec.ratpack.redis.repository.impl;

import java.util.function.Function;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.lukec.ratpack.bo.Transaction;
import com.lukec.ratpack.bo.TransactionList;
import com.lukec.ratpack.redis.RedisClientProvider;
import com.lukec.ratpack.redis.repository.TransactionRepository;

import redis.clients.jedis.ShardedJedis;

public class TransactionRepositoryImpl implements TransactionRepository {
    final static Logger logger = LoggerFactory.getLogger(TransactionRepositoryImpl.class);
    private final RedisClientProvider provider;
    
    @Inject
    public TransactionRepositoryImpl(RedisClientProvider provider) {
	this.provider = provider;
    }

    Function<String, String> getKeyForUser = (id) -> "transaction:" + id + ":";
    
    @Override
    public void spend(String token, Transaction n) {
	ShardedJedis resource = provider.get();
	TransactionList transactions = transactionsForUser(token);
	transactions.addTransaction(n);
	Gson gson = new Gson();
	String json = gson.toJson(transactions);
	logger.debug("Key is "+getKeyForUser.apply(token));
	logger.debug("JSON is "+json);
	resource.set(getKeyForUser.apply(token), json);
	provider.closePool();
    }
    
    @Override
    public TransactionList transactionsForUser(String token) {
	TransactionList transactions = new TransactionList();
	ShardedJedis resource = provider.get();
	String t = resource.get(getKeyForUser.apply(token));
	if(null == t) {
	    return transactions;
	}
	Gson gson = new Gson();
	transactions = gson.fromJson(t, TransactionList.class);
	provider.closePool();
	return transactions;
    }
}
