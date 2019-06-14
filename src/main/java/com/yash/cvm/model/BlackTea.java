package com.yash.cvm.model;

public class BlackTea {

    private Integer teaQuantity=3;
    private Integer waterQuantity=100;
	private Integer sugarQuantity=15;
	private Integer wastedTeaQuantity = 1;
	private Integer wastedWaterQuantity=12;
	private Integer WastedSugarQuantity=2;
	private Double blackTeaPrice=5.0;
	
	
	public Double getBlackTeaPrice() {
		return blackTeaPrice;
	}
	
	public Integer getTeaQuantity() {
		return teaQuantity;
	}
	public Integer getWaterQuantity() {
		return waterQuantity;
	}
	public Integer getSugarQuantity() {
		return sugarQuantity;
	}
	public Integer getWastedTeaQuantity() {
		return wastedTeaQuantity;
	}
	public Integer getWastedWaterQuantity() {
		return wastedWaterQuantity;
	}
	public Integer getWastedSugarQuantity() {
		return WastedSugarQuantity;
	}
	
	
	
}
