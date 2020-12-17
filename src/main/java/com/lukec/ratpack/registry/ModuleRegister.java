package com.lukec.ratpack.registry;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.lukec.ratpack.logging.LoggingHandler;
import com.lukec.ratpack.main.SecretsConfig;
import com.lukec.ratpack.main.endpoint.NonSecureEndpoint;
import com.lukec.ratpack.main.endpoint.SecureEndpoint;
import com.lukec.ratpack.main.handler.ApplicationHandler;
import com.lukec.ratpack.redis.RedisModule;
import com.lukec.ratpack.service.BalanceService;
import com.lukec.ratpack.service.LoginService;
import com.lukec.ratpack.service.TransactionService;
import com.lukec.ratpack.service.impl.BalanceServiceImpl;
import com.lukec.ratpack.service.impl.LoginServiceImpl;
import com.lukec.ratpack.service.impl.TransactionServiceImpl;
import com.lukec.ratpack.main.errors.AppServerErrorHandler;

import ratpack.error.ServerErrorHandler;
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
    bind(LoginService.class).to(LoginServiceImpl.class);
    bind(ApplicationHandler.class);
    bind(NonSecureEndpoint.class);
    bind(SecureEndpoint.class);
    bind(SecretsConfig.class);
    bind(RedisModule.class);
    Multibinder.newSetBinder(binder(), HandlerDecorator.class).addBinding().toInstance(HandlerDecorator.prepend(new LoggingHandler()));
  }


}
