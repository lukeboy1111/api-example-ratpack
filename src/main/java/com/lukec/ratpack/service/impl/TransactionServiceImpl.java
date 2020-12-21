package com.lukec.ratpack.service.impl;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

import javax.inject.Inject;

import com.lukec.ratpack.bo.Transaction;
import com.lukec.ratpack.bo.TransactionList;
import com.lukec.ratpack.bo.TransactionResponse;
import com.lukec.ratpack.bo.UserBalance;
import com.lukec.ratpack.exception.TransactionException;
import com.lukec.ratpack.exception.UserException;
import com.lukec.ratpack.redis.repository.TransactionRepository;
import com.lukec.ratpack.service.BalanceService;
import com.lukec.ratpack.service.TransactionService;

import ratpack.exec.Operation;
import ratpack.exec.Promise;

/**
 * The service implementation.
 *
 */
public class TransactionServiceImpl implements TransactionService {
    
    private TransactionRepository repository;
    private BalanceService balanceService;
    
    @Inject
    public TransactionServiceImpl(TransactionRepository rep, BalanceService balanceService) {
	this.repository = rep;
	this.balanceService = balanceService;
    }
    
    @Override
    public Promise<TransactionList> getTransactions(String token) throws UserException {
	token = token.replace("Bearer ", "");
	try {
	    TransactionList tl = repository.transactionsForUser(token);
	    return Promise.value(tl);
	}
	catch(TransactionException e) {
	    return Promise.error(e);
	}
	
    }

    @Override
    public Operation spend(String token, Transaction n) throws UserException {
	token = token.replace("Bearer ", "");
	if(n.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
	    throw new UserException("Error: Amount must be > 0.00", new IllegalArgumentException());
	}
	Optional<UserBalance> balance = balanceService.getBalance(token);
	if(balance.isPresent()) {
	    	UserBalance balanceNow = balance.get();
        	if(balanceIsNotEnough(balanceNow, n)) {
        	    throw new UserException("Error: Balance Insufficient", new IllegalArgumentException());
        	}
        	balanceService.reduceBalance(token, balanceNow, n.getAmount());
	    	TransactionResponse t = new TransactionResponse("");
        	repository.spend(token, n);
        	return Operation.noop();
	}
	else {
	    throw new UserException("Error: Balance Error", new FileNotFoundException());
	}
    }

    private boolean balanceIsNotEnough(UserBalance balance, Transaction n) {
	if(balance.getBalance().compareTo(n.getAmount()) < 0) {
	    return true;
	}
	return false;
    }

}
