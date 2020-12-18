package com.lukec.ratpack.repository;

import java.util.Map;

import com.lukec.ratpack.bo.UserBalance;

import ratpack.exec.Operation;
import ratpack.exec.Promise;


public interface UserRepository {
    Promise<String> getUserBalanceForUser(String token);
    Promise<Map<String, String>> getUserBalance(String token);
    Operation setUserBalance(String token, UserBalance balance);
}
