package com.yash.cvm.model;

public class BlackCoffee {

	private Integer coffeeQuantity=3;
	private Integer waterQuantity=100;
	private Integer sugarQuantity=15;
	private Integer wasteedCoffeeQuantity = 0;
	private Integer wastedWaterQuantity=12;
	private Integer WastedSugarQuantity=2;
	private double coffeePrice=10.0;
	
	
	public Integer getCoffeeQuantity() {
		return coffeeQuantity;
	}
	public Integer getWaterQuantity() {
		return waterQuantity;
	}
	public Integer getSugarQuantity() {
		return sugarQuantity;
	}
	public Integer getWasteedCoffeeQuantity() {
		return wasteedCoffeeQuantity;
	}
	public Integer getWastedWaterQuantity() {
		return wastedWaterQuantity;
	}
	public Integer getWastedSugarQuantity() {
		return WastedSugarQuantity;
	}
	public double getCoffeePrice() {
		return coffeePrice;
	}
	
	
}
