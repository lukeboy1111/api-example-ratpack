package com.lukec.ratpack.service.impl;

import com.lukec.ratpack.main.handler.ApplicationHandler;
import com.lukec.ratpack.service.TransactionService;

/**
 * The service implementation.
 *
 * @see ApplicationHandler
 */
public class TransactionServiceImpl implements TransactionService {

    public String getValue() {
        return "service-value";
    }

}
