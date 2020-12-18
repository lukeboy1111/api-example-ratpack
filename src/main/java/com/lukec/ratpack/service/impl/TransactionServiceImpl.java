package com.lukec.ratpack.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lukec.ratpack.bo.Transaction;
import com.lukec.ratpack.service.TransactionService;

import ratpack.exec.Promise;
import ratpack.handling.Context;

/**
 * The service implementation.
 *
 * @see ApplicationHandler
 */
public class TransactionServiceImpl implements TransactionService {


    @Override
    public Promise<List<Transaction>> getTransactions(Context ctx) {
	Transaction t = new Transaction(new Date(), "test", BigDecimal.ONE, "GBP");
	List<Transaction> list = new ArrayList<>();
	list.add(t);
	return Promise.value(list);
    }

}
