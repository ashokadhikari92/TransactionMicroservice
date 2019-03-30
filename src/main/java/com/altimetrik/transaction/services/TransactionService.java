package com.altimetrik.transaction.services;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Deque;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.transaction.dtos.TransactionDTO;
import com.altimetrik.transaction.dtos.TransactionStatDTO;
import com.altimetrik.transaction.entities.Transaction;
import com.altimetrik.transaction.exceptions.InvalidTransactionTime;
import com.altimetrik.transaction.repositories.TransactionRepository;
import com.altimetrik.transaction.services.adaptors.TransactionAdapter;
import com.altimetrik.transaction.utils.TransactionUtils;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public TransactionDTO save(TransactionDTO transactionDto) throws InvalidTransactionTime {
//		String givenTime = OffsetDateTime.now(ZoneOffset.UTC).toString();
		
		Boolean valid = TransactionUtils.validateTime(transactionDto.getTime());
//		Boolean valid = TransactionUtils.validateTime(givenTime);
//		transactionDto.setTime(givenTime);
		
		if(valid) {
			Transaction transaction = TransactionAdapter.convertToTransactionEntity(transactionDto);
			transactionRepository.save(transaction);
		}else {
			throw new InvalidTransactionTime("Transaction time is invalid");
		}
		

		return transactionDto;
	}

	public TransactionStatDTO getStats() {
		
		Double avg = getFilteredTransaction().average().orElse(0);

		Double sum = getFilteredTransaction().sum();

		Double max = getFilteredTransaction().max().orElse(0);

		Double min = getFilteredTransaction().min().orElse(0);
		
		Long count = getFilteredTransaction().count();

		return new TransactionStatDTO(sum,avg,max,min,count);
	}
	
	private DoubleStream getFilteredTransaction() {
		Deque<Transaction> transaction = transactionRepository.getAll();
		
		return transaction.stream()
		.filter(t-> TransactionUtils.validateTime(t.getTime()))
		.mapToDouble(Transaction::getAmount);
	}
}
