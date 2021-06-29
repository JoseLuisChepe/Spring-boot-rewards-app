package com.onis.springboot.homework.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long id;
	private String name;
		
}
