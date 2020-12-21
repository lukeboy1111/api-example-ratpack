package com.lukec.ratpack.registry;

import org.pac4j.core.client.Clients;

import com.fasterxml.jackson.core.filter.TokenFilter;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.lukec.ratpack.bo.Transaction;
import com.lukec.ratpack.bo.TransactionList;
import com.lukec.ratpack.bo.UserBalance;
import com.lukec.ratpack.descriptor.LukeCampbell;
import com.lukec.ratpack.logging.LoggingHandler;
import com.lukec.ratpack.main.endpoint.LukeEndpoint;
import com.lukec.ratpack.main.endpoint.NonSecureEndpoint;
import com.lukec.ratpack.main.endpoint.SecureEndpoint;
import com.lukec.ratpack.main.endpoint.TransactionEndpoint;
import com.lukec.ratpack.redis.repository.TransactionRepository;
import com.lukec.ratpack.redis.repository.UserRepository;
import com.lukec.ratpack.redis.repository.impl.TransactionRepositoryImpl;
import com.lukec.ratpack.redis.repository.impl.UserRepositoryImpl;
import com.lukec.ratpack.service.BalanceService;
import com.lukec.ratpack.service.DescriptionService;
import com.lukec.ratpack.service.InitService;
import com.lukec.ratpack.service.LoginService;
import com.lukec.ratpack.service.TransactionService;
import com.lukec.ratpack.service.impl.BalanceServiceImpl;
import com.lukec.ratpack.service.impl.DescriptionServiceImpl;
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
    bind(TransactionService.class).to(TransactionServiceImpl.class);
    bind(BalanceService.class).to(BalanceServiceImpl.class);
    bind(InitService.class).to(InitServiceImpl.class);
    bind(LoginService.class).to(LoginServiceImpl.class);
    bind(DescriptionService.class).to(DescriptionServiceImpl.class);
    
    bind(NonSecureEndpoint.class);
    bind(SecureEndpoint.class);
    bind(TransactionEndpoint.class);
    bind(LukeEndpoint.class);
    
    bind(TokenFilter.class);
    bind(Clients.class);
    
    bind(UserRepository.class).to(UserRepositoryImpl.class);
    bind(TransactionRepository.class).to(TransactionRepositoryImpl.class);
    
    bind(UserBalance.class);
    bind(Transaction.class);
    bind(TransactionList.class);
    bind(LukeCampbell.class);
    Multibinder.newSetBinder(binder(), HandlerDecorator.class).addBinding().toInstance(HandlerDecorator.prepend(new LoggingHandler()));
  }


}
