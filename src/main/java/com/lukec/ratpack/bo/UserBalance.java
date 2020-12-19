package com.lukec.ratpack.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.inject.Inject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserBalance implements Serializable {
	private String currencyCode;
	private BigDecimal balance;
	
	@Inject
	private UserBalance() {
	    this.currencyCode = "";
	    this.balance = BigDecimal.ZERO;
	}
}
