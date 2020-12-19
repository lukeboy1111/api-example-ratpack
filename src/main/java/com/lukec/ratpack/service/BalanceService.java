package com.lukec.ratpack.service;

import java.util.Optional;

import com.lukec.ratpack.bo.UserBalance;

public interface BalanceService {
	Optional<UserBalance> getBalance(String token);
	UserBalance retrieveFullBalance(String token) throws Exception;
}
