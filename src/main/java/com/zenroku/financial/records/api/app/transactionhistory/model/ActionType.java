package com.zenroku.financial.records.api.app.transactionhistory.model;

public enum ActionType {
    DEBIT("Debit"),
    CREDIT("Credit");

    final String readable;

    ActionType(String readable){
        this.readable = readable;
    }

    public String getReadable() {
        return readable;
    }
}
