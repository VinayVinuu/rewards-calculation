package com.retail.rewards.calculation.api;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class RewardsTransactionRequest {
    private final List<RewardsTransaction> transactions;

    @JsonCreator
    public RewardsTransactionRequest(List<RewardsTransaction> transactions) {
        this.transactions = transactions;
    }

    public List<RewardsTransaction> getTransactions() {
        return transactions;
    }

}
