package com.lukec.ratpack.service.impl;

import java.math.BigDecimal;

import org.pac4j.core.profile.UserProfile;

import com.lukec.ratpack.bo.UserBalance;
import com.lukec.ratpack.service.BalanceService;

public class BalanceServiceImpl implements BalanceService {

	@Override
	public UserBalance getBalance(UserProfile profile) {
		UserBalance balance = new UserBalance("GBP", new BigDecimal(0.00f));
		return balance;
	}

}
