package com.retail.rewards.calculation.api;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class RewardsTransactionResponse {

    private final int totalRewardsPoints;
    private final List<RewardsTransactionResult> rewardsResult;

    @JsonCreator
    public RewardsTransactionResponse(final int totalRewardsPoints, final List<RewardsTransactionResult> transactions) {
        this.totalRewardsPoints = totalRewardsPoints;
        this.rewardsResult = transactions;
    }

    public int getTotalRewardsPoints() {
        return totalRewardsPoints;
    }

    public List<RewardsTransactionResult> getRewardsResult() {
        return rewardsResult;
    }
}
