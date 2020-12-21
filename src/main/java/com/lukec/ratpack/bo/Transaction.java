package com.lukec.ratpack.bo;

import java.math.BigDecimal;
import java.util.Date;

import javax.inject.Inject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date date;
    private String description;
    private BigDecimal amount;
    private String currency;

    @Inject
    private Transaction() {
	this.date = new Date();
	this.amount = BigDecimal.ZERO;
	this.description = "";
	this.currency = "";
    }

    @JsonCreator
    public Transaction(@JsonProperty("date") Date theDate, @JsonProperty("description") String description,
	    @JsonProperty("amount") BigDecimal amount, @JsonProperty("currency") String currency) {
	this.date = theDate;
	this.description = description;
	this.amount = amount;
	this.currency = currency;

    }
}
