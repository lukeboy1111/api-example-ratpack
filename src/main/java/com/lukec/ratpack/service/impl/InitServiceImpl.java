package com.lukec.ratpack.service.impl;

import java.util.Optional;

import javax.inject.Inject;

import com.lukec.ratpack.bo.UserBalance;
import com.lukec.ratpack.redis.UserRepository;
import com.lukec.ratpack.service.InitService;

public class InitServiceImpl implements InitService {
    
    private UserRepository userRepository;
    
    @Inject
    public InitServiceImpl(UserRepository rep) {
	this.userRepository = rep;
    }
    
    @Override
    public void checkUserInitialised(final String token) {
	System.err.println("CheckUser");
	Optional<UserBalance> balance = userRepository.getUserBalanceForUser(token);
	if(balance.isPresent()) {
	    return;
	}
	System.err.println("Make Default Balance");
	userRepository.makeDefaultBalance(token);
	System.err.println("Exit CheckUser");
    }

}
