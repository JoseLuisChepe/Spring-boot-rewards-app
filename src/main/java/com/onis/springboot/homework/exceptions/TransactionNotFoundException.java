package com.onis.springboot.homework.exceptions;

public class TransactionNotFoundException extends RuntimeException {

	public TransactionNotFoundException(String exception) {
		super(exception);
	}

}
