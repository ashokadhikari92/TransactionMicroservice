package com.altimetrik.transaction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.transaction.dtos.TransactionDTO;
import com.altimetrik.transaction.dtos.TransactionStatDTO;
import com.altimetrik.transaction.exceptions.InvalidTransactionTime;
import com.altimetrik.transaction.services.TransactionService;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody TransactionDTO transactionDto) {
		if (transactionDto.getAmount() != null && transactionDto != null) {
			TransactionDTO dto;
			try {
				dto = transactionService.save(transactionDto);
				return new ResponseEntity<TransactionDTO>(dto, HttpStatus.CREATED);
			} catch (InvalidTransactionTime e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
			}
		} else {
			return new ResponseEntity<String>("Invalid Data", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping
	public ResponseEntity<?> getStats() {
		TransactionStatDTO transactionStat = transactionService.getStats();

		return new ResponseEntity<TransactionStatDTO>(transactionStat, HttpStatus.OK);
	}

}
