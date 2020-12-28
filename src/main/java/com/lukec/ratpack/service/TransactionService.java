package com.lukec.ratpack.service;

import com.lukec.ratpack.bo.Transaction;
import com.lukec.ratpack.bo.TransactionList;
import com.lukec.ratpack.exception.TransactionException;
import com.lukec.ratpack.exception.UserException;

import ratpack.exec.Promise;

/**
 * An example service interface.
 *
 * @see ApplicationHandler
 */
public interface TransactionService {

    Promise<TransactionList> getTransactions(String token) throws TransactionException;
    Promise<Transaction> spend(String token, Transaction n) throws UserException;
}
