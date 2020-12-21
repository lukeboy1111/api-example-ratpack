package com.lukec.ratpack.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.lukec.ratpack.bo.UserBalance;
import com.lukec.ratpack.exception.UserException;

public interface BalanceService {
	Optional<UserBalance> getBalance(String token);
	UserBalance retrieveFullBalance(String token) throws UserException;
	void reduceBalance(String token, UserBalance balance, BigDecimal amount) throws UserException;
	void setBalance(String token, UserBalance balance);
}
