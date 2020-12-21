package com.lukec.ratpack.bo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionList {
    private List<Transaction> listTransactions;
    
    public void addTransaction(Transaction t) {
	listTransactions.add(t);
    }

    public TransactionList() {
	listTransactions = new ArrayList<>();
    }
    
}
