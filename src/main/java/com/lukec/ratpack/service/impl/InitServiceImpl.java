package com.lukec.ratpack.service.impl;

import com.lukec.ratpack.repository.UserRepository;
import com.lukec.ratpack.service.InitService;

import ratpack.handling.Context;

public class InitServiceImpl implements InitService {

    @Override
    public void checkUserInitialised(final Context ctx, String token) {
	// TODO Auto-generated method stub
	UserRepository userRepository = ctx.get(UserRepository.class);
	//Promise<String> balance = userRepository.getUserBalanceForUser(token);
	
    }

}
