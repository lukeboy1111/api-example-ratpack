package com.lukec.ratpack.service;

import com.lukec.ratpack.bo.UserBalance;

public interface BalanceService {
	UserBalance getBalance(String token);
}
