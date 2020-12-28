package com.lukec.ratpack.redis;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.lukec.ratpack.redis.repository.TransactionRepository;
import com.lukec.ratpack.redis.repository.UserRepository;
import com.lukec.ratpack.redis.repository.impl.TransactionRepositoryImpl;
import com.lukec.ratpack.redis.repository.impl.UserRepositoryImpl;

public class ConfigurationModule extends AbstractModule {
    
    @Inject
    private RedisClientProvider clientProvider;
    
    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    public UserRepository userRepository(RedisClientProvider provider) {
	    return new UserRepositoryImpl(provider);
    }
    
    @Provides
    @Singleton
    public TransactionRepository TransactionRepository(RedisClientProvider provider) {
	    return new TransactionRepositoryImpl(provider);
    }
}
