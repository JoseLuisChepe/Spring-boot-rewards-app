package com.onis.springboot.homework.model;

import lombok.Data;

import java.util.List;

@Data
public class RewardByCustomer {
    List<RewardByMonth> rewards;
    int total;
}
