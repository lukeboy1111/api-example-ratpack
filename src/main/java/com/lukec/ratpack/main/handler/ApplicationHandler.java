package com.lukec.ratpack.main.handler;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.lukec.ratpack.module.ModuleRegister;
import com.lukec.ratpack.service.TransactionService;

/**
 * A handler implementation that is created via dependency injection.
 *
 * @see ModuleRegister
 */
@Singleton
public class ApplicationHandler implements Handler {

  private final TransactionService myService;

  @Inject
  public ApplicationHandler(TransactionService myService) {
    this.myService = myService;
  }

  @Override
  public void handle(Context context) {
    context.render("service value: " + myService.getValue());
  }
}
