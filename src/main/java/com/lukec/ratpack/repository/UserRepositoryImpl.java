package com.lukec.ratpack.repository;

import java.util.Map;

import com.lukec.ratpack.bo.UserBalance;

import ratpack.exec.Operation;
import ratpack.exec.Promise;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public Promise<String> getUserBalanceForUser(String token) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Promise<Map<String, String>> getUserBalance(String token) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Operation setUserBalance(String token, UserBalance balance) {
	// TODO Auto-generated method stub
	return null;
    }
    
}
