package com.lukec.ratpack.service;

import org.pac4j.core.profile.UserProfile;

import com.lukec.ratpack.bo.UserBalance;

public interface BalanceService {
	UserBalance getBalance(UserProfile profile);
}
