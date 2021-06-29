package com.onis.springboot.homework.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private Long customer;
    private Double amount;
    private Integer month;
}

