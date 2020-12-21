package com.lukec.ratpack.redis.repository;

import com.lukec.ratpack.bo.Transaction;
import com.lukec.ratpack.bo.TransactionList;
import com.lukec.ratpack.exception.TransactionException;

public interface TransactionRepository {

    void spend(String token, Transaction n);
    TransactionList transactionsForUser(String token) throws TransactionException;
}
