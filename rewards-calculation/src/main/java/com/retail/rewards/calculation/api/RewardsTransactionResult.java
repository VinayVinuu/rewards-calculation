package com.retail.rewards.calculation.api;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RewardsTransactionResult {
    private final String transactionId;
    private final double rewardsPoints;

    @JsonCreator
    public RewardsTransactionResult(final String transactionId, final double rewardsPoints) {
        this.transactionId = transactionId;
        this.rewardsPoints = rewardsPoints;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getRewardsPoints() {
        return rewardsPoints;
    }
}
