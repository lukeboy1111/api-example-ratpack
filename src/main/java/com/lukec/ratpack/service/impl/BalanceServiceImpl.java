package com.lukec.ratpack.service.impl;

import java.math.BigDecimal;

import com.lukec.ratpack.bo.UserBalance;
import com.lukec.ratpack.service.BalanceService;

import ratpack.exec.Promise;
import ratpack.handling.Context;

public class BalanceServiceImpl implements BalanceService {

	@Override
	public Promise<UserBalance> getBalance(Context ctx) {
		UserBalance balance = new UserBalance("GBP", new BigDecimal(0.00f));
		return Promise.value(balance);
	}

}
