package com.lukec.ratpack.bo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserBalance {
	private String currencyCode;
	private BigDecimal balance;
}
