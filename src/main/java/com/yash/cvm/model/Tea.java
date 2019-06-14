package com.yash.cvm.model;


public class Tea {
	
	private Integer teaQuantity=5;
	private Integer waterQuantity=60;
	private Integer milkQuantity=40;
	private Integer sugarQuantity=15;
	private Integer wasteedTeaQuantity = 1;
	private Integer wastedWaterQuantity=5;
	private Integer wastedMilkQuantity=4;
	private Integer WastedSugarQuantity=2;
	private Double teaPrice=10.0;
	
	public Double getTeaPrice() {
		return teaPrice;
	}

	

	public Tea() {
		
	}
	
	public Integer getTeaQuantity() {
		return teaQuantity;
	}
	
	public Integer getWaterQuantity() {
		return waterQuantity;
	}
	
	public Integer getMilkQuantity() {
		return milkQuantity;
	}
	
	public Integer getSugarQuantity() {
		return sugarQuantity;
	}
	
	public Integer getWasteedTeaQuantity() {
		return wasteedTeaQuantity;
	}
	
	public Integer getWastedWaterQuantity() {
		return wastedWaterQuantity;
	}
	
	public Integer getWastedMilkQuantity() {
		return wastedMilkQuantity;
	}
	
	public Integer getWastedSugarQuantity() {
		return WastedSugarQuantity;
	}
	
	
	
	
}
