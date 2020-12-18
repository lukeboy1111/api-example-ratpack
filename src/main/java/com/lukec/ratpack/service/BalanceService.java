package com.lukec.ratpack.service;

import com.lukec.ratpack.bo.UserBalance;

import ratpack.exec.Promise;
import ratpack.handling.Context;

public interface BalanceService {
	Promise<UserBalance> getBalance(Context ctx);
}
