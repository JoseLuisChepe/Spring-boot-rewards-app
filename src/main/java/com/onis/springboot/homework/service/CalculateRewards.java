package com.onis.springboot.homework.service;

import com.onis.springboot.homework.model.Customer;
import com.onis.springboot.homework.model.RewardByCustomer;
import com.onis.springboot.homework.model.RewardByMonth;
import com.onis.springboot.homework.model.Transaction;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculateRewards {
    public static Map<String, RewardByCustomer> calculateRewardByClient(List<Customer> customers, List<Transaction> transactions){
        Map<Long, String> customerNames = new HashMap<>();
        Map<Long, Map<Integer, Integer>> totalByCustomer = new HashMap<>();
        customers.forEach(customer -> {
            customerNames.put(customer.getId(), customer.getName());
        });
        transactions.forEach(transaction -> {
            int totalTransaction;
            double amount = transaction.getAmount();
            if(amount>50){
                if(amount>100){
                    totalTransaction = 50 + (int) Math.round((amount-100)*2);
                }else {
                    totalTransaction = (int) Math.round(amount) - 50;
                }
                Long customer = transaction.getCustomer();
                int month = transaction.getMonth();
                if(totalByCustomer.get(customer)!=null){
                    if(totalByCustomer.get(customer).get(month)!=null){
                        int totalAccumulated = totalByCustomer.get(customer).get(month)+totalTransaction;
                        totalByCustomer.get(customer).put(month, totalAccumulated);
                    }else{
                        totalByCustomer.get(customer).put(month, totalTransaction);
                    }
                }else{
                    Map <Integer, Integer> tempMapMonthAmount = new HashMap<>();
                    tempMapMonthAmount.put(transaction.getMonth(), totalTransaction);
                    totalByCustomer.put(transaction.getCustomer(),tempMapMonthAmount );
                }
            }
        });
        return transformData(totalByCustomer, customerNames);
    }

    public static Map<String, RewardByCustomer> transformData(Map<Long, Map<Integer, Integer>> totalByCustomer, Map<Long, String> customerNames){
        Map<String, RewardByCustomer> responseFormated = new HashMap<>();
        totalByCustomer.forEach((key, val) -> {
            List<RewardByMonth> rewardByMonthList = new ArrayList<>();
            int total = 0;
            for(Map.Entry<Integer, Integer> entry: val.entrySet()){
                RewardByMonth rewardByMonth = new RewardByMonth();
                rewardByMonth.setMonth(new DateFormatSymbols().getMonths()[entry.getKey()-1]);
                rewardByMonth.setAmount(entry.getValue());
                rewardByMonthList.add(rewardByMonth);
                total+=entry.getValue();
            }
            RewardByCustomer rewardByCustomer= new RewardByCustomer();
            rewardByCustomer.setRewards(rewardByMonthList);
            rewardByCustomer.setTotal(total);
            responseFormated.put(customerNames.get(key), rewardByCustomer);
        });
        return responseFormated;
    }
}
