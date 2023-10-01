package com.retail.rewards.calculation.api;

public class RewardsTransaction {
    private final String transactionId;
    private final String transactionDate;
    private final float transactionAmount;

    public RewardsTransaction(final String transactionId, final float transactionAmount, final String transactionDate) {
        this.transactionId = transactionId;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }
}
