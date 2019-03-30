package com.altimetrik.transaction.utils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class TransactionUtils {

	public static boolean validateTime(String transactionTime) {
		OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
		System.out.println(utc.toString());
		
		ZonedDateTime dateTime = ZonedDateTime.parse(transactionTime);
//		dateTime = dateTime.plusSeconds(60);
		System.out.println("Given Time : "+ transactionTime);
		System.out.println("Current time : "+ utc.toString());
		
		int year = dateTime.getYear();
		int month = dateTime.getMonthValue();
		int day = dateTime.getDayOfMonth();
		int dayOfYear = dateTime.getDayOfYear();
		int hours = dateTime.getHour();
		int minutes = dateTime.getMinute();
		int seconds = dateTime.getSecond();
		
		if(year == utc.getYear() && month == utc.getMonthValue() && day == utc.getDayOfMonth() && hours == utc.getHour() && minutes == utc.getMinute() && seconds <= utc.getSecond()) {
			return true;
		}
		
		return false;
	}
}
