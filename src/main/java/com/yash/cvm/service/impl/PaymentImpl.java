package com.yash.cvm.service.impl;

public class PaymentImpl {
	
	public double calculatePriceForOrder(String orderType, Double costPerCup, Integer orderQuantity,
			Integer insertedAmount) {

		return (costPerCup * orderQuantity) - Double.parseDouble(String.valueOf(insertedAmount));

	}

}
