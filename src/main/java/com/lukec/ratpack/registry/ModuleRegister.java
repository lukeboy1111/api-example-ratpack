package com.lukec.ratpack.registry;

import org.pac4j.core.client.Clients;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.lukec.ratpack.bo.UserBalance;
import com.lukec.ratpack.logging.LoggingHandler;
import com.lukec.ratpack.main.endpoint.NonSecureEndpoint;
import com.lukec.ratpack.main.endpoint.SecureEndpoint;
import com.lukec.ratpack.redis.RedisModule;
import com.lukec.ratpack.redis.UserRepository;
import com.lukec.ratpack.redis.UserRepositoryImpl;
import com.lukec.ratpack.service.BalanceService;
import com.lukec.ratpack.service.InitService;
import com.lukec.ratpack.service.LoginService;
import com.lukec.ratpack.service.TransactionService;
import com.lukec.ratpack.service.impl.BalanceServiceImpl;
import com.lukec.ratpack.service.impl.InitServiceImpl;
import com.lukec.ratpack.service.impl.LoginServiceImpl;
import com.lukec.ratpack.service.impl.TransactionServiceImpl;

import ratpack.handling.HandlerDecorator;

/**
 * An example Guice module.
 */
public class ModuleRegister extends AbstractModule {

  /**
   * Adds a service impl to the application, and registers a decorator so that all requests are logged.
   * Registered implementations of {@link ratpack.handling.HandlerDecorator} are able to decorate the application handler.
   *
   * @see ApplicationHandler
   */
  protected void configure() {
    bind(RedisModule.class);
    bind(TransactionService.class).to(TransactionServiceImpl.class);
    bind(BalanceService.class).to(BalanceServiceImpl.class);
    bind(InitService.class).to(InitServiceImpl.class);
    bind(LoginService.class).to(LoginServiceImpl.class);
    
    bind(NonSecureEndpoint.class);
    bind(SecureEndpoint.class);
    
    //bind(SessionModule.class);
    bind(Clients.class);
    //bind(RedisModule.class);
    
    bind(UserRepository.class).to(UserRepositoryImpl.class);
    bind(UserBalance.class);
    //bind(RedisClientProvider.class);
    Multibinder.newSetBinder(binder(), HandlerDecorator.class).addBinding().toInstance(HandlerDecorator.prepend(new LoggingHandler()));
  }


}
