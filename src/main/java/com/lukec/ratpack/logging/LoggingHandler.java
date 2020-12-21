package com.lukec.ratpack.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lukec.ratpack.registry.ModuleRegister;

import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * An example of a handler implicitly set up by a module
 *
 * @see ModuleRegister
 */
public class LoggingHandler implements Handler {
    final static Logger logger = LoggerFactory.getLogger(LoggingHandler.class);
    @Override
    public void handle(Context context) {
	logger.warn("Received: " + context.getRequest().getUri());
        context.next();
    }
}
