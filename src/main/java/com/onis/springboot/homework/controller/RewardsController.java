package com.onis.springboot.homework.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.onis.springboot.homework.model.Customer;
import com.onis.springboot.homework.model.RewardByCustomer;
import com.onis.springboot.homework.repository.CustomerRepository;
import com.onis.springboot.homework.repository.TransactionRepository;
import com.onis.springboot.homework.model.Transaction;
import com.onis.springboot.homework.service.CalculateRewards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class RewardsController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@GetMapping("/transactions")
	public List<Transaction> retrieveAllTransactions() {
		customerRepository.findAll();
		return transactionRepository.findAll();
	}

	@GetMapping("/customers")
	public List<Customer> retrieveAllCustomers() {
		return customerRepository.findAll();

	}

	@GetMapping("/rewardByCustomer")
	public Map<String, RewardByCustomer> retrieveRewards() {
		List<Transaction> transactions = transactionRepository.findAll();
		List<Customer> customers = customerRepository.findAll();
		return CalculateRewards.calculateRewardByClient(customers, transactions);
	}

	@DeleteMapping("/transactions/{id}")
	public void deleteTransaction(@PathVariable long id) {
		transactionRepository.deleteById(id);
	}

	@PostMapping("/transactions")
	public ResponseEntity<Object> createTransaction(@RequestBody Transaction transaction) {
		Transaction savedTransaction = transactionRepository.save(transaction);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedTransaction.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/transactions/{id}")
	public ResponseEntity<Object> updateTransaction(@RequestBody Transaction transaction, @PathVariable long id) {

		Optional<Transaction> transactionOptional = transactionRepository.findById(id);

		if (!transactionOptional.isPresent())
			return ResponseEntity.notFound().build();

		transaction.setId(id);

		transactionRepository.save(transaction);

		return ResponseEntity.noContent().build();
	}
}
