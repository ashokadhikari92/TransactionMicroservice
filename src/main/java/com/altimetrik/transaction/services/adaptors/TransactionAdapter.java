package com.altimetrik.transaction.services.adaptors;

import com.altimetrik.transaction.dtos.TransactionDTO;
import com.altimetrik.transaction.entities.Transaction;

public class TransactionAdapter {

	public static Transaction convertToTransactionEntity(TransactionDTO transactionDto) {
		Double amount = transactionDto.getAmount();
		String time = transactionDto.getTime();
		
		return new Transaction(amount,time);
	}
	
	public static TransactionDTO convertToTransactionDTO(Transaction transaction) {
		Double amount = transaction.getAmount();
		String time = transaction.getTime();
		
		return new TransactionDTO(amount,time);
	}
}
