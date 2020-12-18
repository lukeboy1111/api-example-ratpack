package com.lukec.ratpack.service;

import java.util.List;

import com.lukec.ratpack.bo.Transaction;

import ratpack.exec.Promise;
import ratpack.handling.Context;

/**
 * An example service interface.
 *
 * @see ApplicationHandler
 */
public interface TransactionService {

    Promise<List<Transaction>> getTransactions(Context ctx);

}
