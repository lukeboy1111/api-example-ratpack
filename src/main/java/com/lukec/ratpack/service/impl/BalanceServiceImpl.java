package com.lukec.ratpack.service.impl;

import java.util.Optional;

import javax.inject.Inject;

import com.lukec.ratpack.bo.UserBalance;
import com.lukec.ratpack.redis.UserRepository;
import com.lukec.ratpack.service.BalanceService;

public class BalanceServiceImpl implements BalanceService {

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
    public UserBalance retrieveFullBalance(String token) throws Exception {
	token = token.replace("Bearer ", "");
	Optional<UserBalance> bal = userRepository.getUserBalanceForUser(token);
	if(bal.isPresent()) {
	    return bal.get();
	}
	else {
	    throw new Exception("Balance Not Present");
	}
    }

}
