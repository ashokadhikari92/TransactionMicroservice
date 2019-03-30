package com.altimetrik.transaction.dtos;

public class TransactionDTO {

	private Double amount;
	private String time;
	
	public TransactionDTO() {}
	
	public TransactionDTO(Double amount, String time) {
		this.amount = amount;
		this.time = time;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
