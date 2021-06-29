package com.onis.springboot.homework.model;

import lombok.Data;

import java.time.Month;

@Data
public class RewardByMonth {
    String month;
    int amount;
}
