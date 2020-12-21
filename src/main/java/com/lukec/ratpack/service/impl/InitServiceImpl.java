package com.lukec.ratpack.service.impl;

import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lukec.ratpack.bo.UserBalance;
import com.lukec.ratpack.redis.repository.UserRepository;
import com.lukec.ratpack.service.InitService;

public class InitServiceImpl implements InitService {
    final static Logger logger = LoggerFactory.getLogger(InitServiceImpl.class);
    private UserRepository repository;
    
    @Inject
    public InitServiceImpl(UserRepository rep) {
	this.repository = rep;
    }
    
    @Override
    public void checkUserInitialised(final String token) {
	logger.debug("CheckUser");
	Optional<UserBalance> balance = repository.getUserBalanceForUser(token);
	if(balance.isPresent()) {
	    return;
	}
	logger.debug("Make Default Balance");
	repository.makeDefaultBalance(token);
	logger.debug("Exit CheckUser");
    }

}
