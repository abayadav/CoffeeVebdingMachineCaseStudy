package com.yash.cvm.TeaCoffeeVendingMachine;
import com.yash.cvm.model.Product;


public class TeaCoffeeVendingMachine {

	private static Order order=new Order();
	private static  Product product=new Product();
	
	
	public static void main(String[] args) {
		
		 order.startMenu(product);
	}
}
