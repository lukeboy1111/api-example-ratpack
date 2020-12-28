package com.lukec.ratpack.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lukec.ratpack.bo.UserBalance;
import com.lukec.ratpack.exception.UserException;
import com.lukec.ratpack.redis.repository.UserRepository;
import com.lukec.ratpack.service.BalanceService;

public class BalanceServiceImpl implements BalanceService {
    final static Logger logger = LoggerFactory.getLogger(BalanceServiceImpl.class);
    private UserRepository userRepository;

    @Inject
    public BalanceServiceImpl(UserRepository rep) {
	this.userRepository = rep;
    }

    @Override
    public Optional<UserBalance> getBalance(String token) {
	token = token.replace("Bearer ", "");
	return userRepository.getUserBalanceForUser(token);
    }

    @Override
    public UserBalance retrieveFullBalance(String token) throws UserException {
	if(token == null) {
	    throw new UserException("Token Not Present", new IllegalArgumentException());
	}
	token = token.replace("Bearer ", "");
	Optional<UserBalance> bal = userRepository.getUserBalanceForUser(token);
	if(bal.isPresent()) {
	    return bal.get();
	}
	else {
	    throw new UserException("Balance Not Present", new IllegalArgumentException());
	}
    }

    @Override
    public void reduceBalance(String token, UserBalance balance, BigDecimal amount) throws UserException {
	BigDecimal balanceDue = balance.getBalance();
	logger.warn("Balance was "+balanceDue+" amount is "+amount);
	balanceDue = balanceDue.subtract(amount);
	balance.setBalance(balanceDue);
	logger.warn("Balance is now :"+balanceDue);
	this.setBalance(token, balance);
    }

    @Override
    public void setBalance(String token, UserBalance balance) {
	userRepository.setUserBalance(token, balance);
    }

}
