package com.retail.rewards.calculation.controller;

import com.retail.rewards.calculation.api.RewardsTransactionRequest;
import com.retail.rewards.calculation.api.RewardsTransactionResponse;
import com.retail.rewards.calculation.service.RewardCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;
import java.util.Map;

@RestController
public class RewardController {
    private final RewardCalculationService rewardCalculationService;

    @Autowired
    public RewardController(final RewardCalculationService rewardCalculationService) {
        this.rewardCalculationService = rewardCalculationService;
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/calculateRewardPoints", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RewardsTransactionResponse> calculateRewardPoints(@RequestBody RewardsTransactionRequest transactionRequest) {
        try {
            RewardsTransactionResponse rewardPoints = rewardCalculationService.calculateRewardPoints(transactionRequest);
            return ResponseEntity.ok(rewardPoints);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/calculateMonthlyRewards", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<YearMonth, Integer>> calculateMonthlyRewards(@RequestBody RewardsTransactionRequest transactionRequest) {
        try {
            Map<YearMonth, Integer> rewardPoints = rewardCalculationService.calculateMonthlyRewards(transactionRequest);
            return ResponseEntity.ok(rewardPoints);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
