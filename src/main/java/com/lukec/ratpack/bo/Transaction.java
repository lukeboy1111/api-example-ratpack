package com.lukec.ratpack.bo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    private Date date;
    private String description; 
    private BigDecimal amount;
    private String currency;
}
