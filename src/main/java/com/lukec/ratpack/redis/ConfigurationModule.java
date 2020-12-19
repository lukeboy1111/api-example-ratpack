package com.lukec.ratpack.redis;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class ConfigurationModule extends AbstractModule {
    
    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    public UserRepository userRepository(RedisClientProvider provider) {
	    return new UserRepositoryImpl(provider);
    }
}
