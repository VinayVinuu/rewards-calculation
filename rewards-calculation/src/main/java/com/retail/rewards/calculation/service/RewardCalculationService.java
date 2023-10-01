package com.retail.rewards.calculation.service;

import com.retail.rewards.calculation.api.RewardsTransaction;
import com.retail.rewards.calculation.api.RewardsTransactionRequest;
import com.retail.rewards.calculation.api.RewardsTransactionResponse;
import com.retail.rewards.calculation.api.RewardsTransactionResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardCalculationService {

    public RewardsTransactionResponse calculateRewardPoints(final RewardsTransactionRequest transactionRequest) {
        final List<RewardsTransactionResult> rewardsTransactionResults = new ArrayList<>();
        final List<RewardsTransaction> transactions = transactionRequest.getTransactions();
        double finalRewardPoints = 0;
        for (final RewardsTransaction transaction : transactions) {
            final double rewardPoints = getRewardPoints(transaction);
            final RewardsTransactionResult result = new RewardsTransactionResult(transaction.getTransactionId(), rewardPoints);
            rewardsTransactionResults.add(result);
            finalRewardPoints += rewardPoints;
        }
        return new RewardsTransactionResponse((int) finalRewardPoints, rewardsTransactionResults);
    }

    private static Integer getRewardPoints(final RewardsTransaction transaction) {
        double rewardPoints = 0;
        if (transaction.getTransactionAmount() > 100) {
            double amountOver100 = transaction.getTransactionAmount() - 100;
            rewardPoints += (amountOver100 * 2);
        }
        if (transaction.getTransactionAmount() > 50) {
            double amountBetween50And100 = Math.min(transaction.getTransactionAmount(), 100) - 50;
            rewardPoints += amountBetween50And100;
        }
        rewardPoints = Math.max(0, rewardPoints);
        return (int) rewardPoints;
    }

    public Map<YearMonth, Integer> calculateMonthlyRewards(final RewardsTransactionRequest transactionRequest) {
        final Map<YearMonth, Integer> monthlyRewards = new HashMap<>();
        final List<RewardsTransaction> transactions = transactionRequest.getTransactions();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final LocalDate currentDate = LocalDate.now();
        final LocalDate threeMonthsAgo = currentDate.minusMonths(2);
        for (final RewardsTransaction transaction : transactions) {
            final String transactionDate = transaction.getTransactionDate();
            final LocalDate localDate = LocalDate.parse(transactionDate, formatter);
            if (!localDate.isBefore(threeMonthsAgo)) {
                final YearMonth yearMonth = YearMonth.of(localDate.getYear(), localDate.getMonth());
                final Integer rewardPoints = getRewardPoints(transaction);
                monthlyRewards.merge(yearMonth, rewardPoints, Integer::sum);
            }
        }
        return monthlyRewards;
    }
}
