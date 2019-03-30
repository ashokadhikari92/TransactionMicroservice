package com.altimetrik.transaction.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.altimetrik.transaction.dtos.TransactionDTO;
import com.altimetrik.transaction.entities.Transaction;
import com.altimetrik.transaction.exceptions.InvalidTransactionTime;
import com.altimetrik.transaction.repositories.TransactionRepository;

public class TransectionServiceTest {

	@InjectMocks
	TransactionService transactionService;
	
	@Mock
	TransactionRepository transactionRepository;
	
	private TransactionDTO validTransactionDto,inValidTransactionDto;
	private Transaction validTransaction,inValidTransaction;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		String currentTime = OffsetDateTime.now(ZoneOffset.UTC).toString();
		validTransactionDto = new TransactionDTO(34.90, currentTime);
		inValidTransactionDto = new TransactionDTO(34.90, "2019-03-29T18:23:32.534Z");
		validTransaction = new Transaction(34.90, currentTime);
		inValidTransaction = new Transaction(34.90, "2019-03-29T18:23:32.534Z");

	}

	@Test
	public void testSave() {
		TransactionDTO result = null;
		when(transactionRepository.save(validTransaction)).thenReturn(true);
		try {
			result = transactionService.save(validTransactionDto);
		} catch (InvalidTransactionTime e) {
			fail("Transaction save test failed.");
		}

		assertTrue(result != null);
		assertEquals(validTransactionDto.getAmount(), result.getAmount());
		assertEquals(validTransactionDto.getTime(), result.getTime());
	}
	
	@Test
	public void testSaveWithException() {
		when(transactionRepository.save(inValidTransaction)).thenReturn(false);
		
		try {
			transactionService.save(inValidTransactionDto);
			fail("Exception should occur");
		} catch (InvalidTransactionTime e) {
			assertTrue(true);
		}
		
	}
}
