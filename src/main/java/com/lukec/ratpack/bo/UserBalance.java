package com.lukec.ratpack.bo;

import java.math.BigDecimal;

import javax.inject.Inject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBalance {

    private String currencyCode;
    private BigDecimal balance;

    @Inject
    private UserBalance() {
	this.currencyCode = "";
	this.balance = BigDecimal.ZERO;
    }

    @JsonCreator
    public UserBalance(@JsonProperty("currencyCode") String currencyCode, @JsonProperty("balance") BigDecimal balance) {
	this.currencyCode = currencyCode;
	this.balance = balance;
    }
}
