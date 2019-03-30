package com.altimetrik.transaction.entities;

public class Transaction {
	private Double amount;
	private String time;
	
	public Transaction() {}
	
	public Transaction(Double amount, String time) {
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
