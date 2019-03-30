package com.altimetrik.transaction.repositories;

import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import org.springframework.stereotype.Repository;
import com.altimetrik.transaction.entities.Transaction;

@Repository
public class TransactionRepository {

	private ConcurrentLinkedDeque<Transaction> transactions = new ConcurrentLinkedDeque<>();
	
	public boolean save(Transaction transaction) {
		return transactions.add(transaction);
	}
	
	public Deque<Transaction> getAll(){
		return transactions;
	}
}
