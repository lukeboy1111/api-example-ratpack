package com.lukec.ratpack.redis.repository;

import java.util.Optional;

import com.lukec.ratpack.bo.UserBalance;


public interface UserRepository {
    Optional<UserBalance> getUserBalanceForUser(String token);
    void setUserBalance(String token, UserBalance balance);
    void makeDefaultBalance(String token);
}
