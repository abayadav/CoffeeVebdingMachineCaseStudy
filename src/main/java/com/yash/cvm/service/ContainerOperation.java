package com.yash.cvm.service;


import com.yash.cvm.model.BlackCoffee;
import com.yash.cvm.model.BlackTea;
import com.yash.cvm.model.Coffee;
import com.yash.cvm.model.Product;
import com.yash.cvm.model.Tea;

public class ContainerOperation {

    Tea tea=new Tea();
    BlackTea blackTea=new BlackTea();
    Coffee coffee=new Coffee();
    BlackCoffee blackCoffee=new BlackCoffee();
    

	public boolean checkAvailabilty(String productType, Integer quantity, Product product) {
		if (productType.equalsIgnoreCase("Tea")) {
			
			
			if ((product.getTeaContainerCapacity() - quantity * tea.getTeaQuantity() < 0)
					|| (product.getSugarContainerCapacity() - quantity * tea.getSugarQuantity() < 0)
					|| (product.getWaterContainerCapacity() - quantity * tea.getWaterQuantity() < 0)
					|| (product.getMilkContainerCapacity() - quantity * tea.getMilkQuantity() < 0)) {
				return false;
			}
			return true;
		} 
       if (productType.equalsIgnoreCase("Black Tea")) {

    	   if ((product.getTeaContainerCapacity() - quantity * blackTea.getTeaQuantity() < 0)
					||  (product.getWaterContainerCapacity() - quantity * blackTea.getWaterQuantity() < 0)
					|| (product.getSugarContainerCapacity() - quantity * blackTea.getSugarQuantity() < 0)) {
				return false;
			}
			return true;
		} else if (productType.equalsIgnoreCase("Coffee")) {
			if ((product.getCoffeeContainerCapacity() - quantity * coffee.getCoffeeQuantity()  < 0)
					|| (product.getSugarContainerCapacity() - quantity * coffee.getSugarQuantity() < 0)
					|| (product.getWaterContainerCapacity() - quantity * coffee.getWaterQuantity() < 0)
					|| (product.getMilkContainerCapacity() - quantity * coffee.getMilkQuantity() < 0)) {
				return false;
			}
			return true;
		} else if (productType.equalsIgnoreCase("Black Coffee")) {
			if ((product.getCoffeeContainerCapacity() - quantity * blackCoffee.getCoffeeQuantity() < 0)
					|| (product.getSugarContainerCapacity() - quantity * blackCoffee.getSugarQuantity() < 0)
					|| (product.getWaterContainerCapacity() - quantity * blackCoffee.getWaterQuantity() < 0)) {
				return false;
			}
			return true;
		}
       
       
		return false;
	}

	
	public void adjustContainerQuantity(String productType, Integer quantity, Product product) {
		if (productType.equalsIgnoreCase("Tea")) {
			product.setTeaContainerCapacity(product.getTeaContainerCapacity() - (quantity * tea.getTeaQuantity() + quantity * tea.getWasteedTeaQuantity()));
			product.setWaterContainerCapacity(product.getWaterContainerCapacity() - (quantity * tea.getWaterQuantity() + quantity * tea.getWastedWaterQuantity()));
			product.setSugarContainerCapacity(product.getSugarContainerCapacity() - (quantity * tea.getSugarQuantity() + quantity * tea.getWastedSugarQuantity()));
			product.setMilkContainerCapacity(product.getMilkContainerCapacity() - (quantity * tea.getMilkQuantity() + quantity * tea.getWastedMilkQuantity()));
            
			product.setTeaWasteAmount(product.getTeaWasteAmount() + quantity * tea.getWasteedTeaQuantity());
			product.setWaterWasteAmount(product.getWaterWasteAmount() + quantity * tea.getWastedWaterQuantity());
			product.setSugarWasteAmount(product.getSugarWasteAmount() + quantity * tea.getWastedSugarQuantity());
			product.setMilkWasteAmount(product.getMilkWasteAmount() + quantity * tea.getWastedMilkQuantity());

		}else if (productType.equalsIgnoreCase("Black Tea")) {
			
			product.setTeaContainerCapacity(product.getTeaContainerCapacity() - (quantity * (blackTea.getTeaQuantity()+blackTea.getWastedTeaQuantity())));
			product.setWaterContainerCapacity(product.getWaterContainerCapacity() - (quantity *( blackTea.getWaterQuantity()+blackTea.getWastedWaterQuantity())));
			product.setSugarContainerCapacity(product.getSugarContainerCapacity() - (quantity * ( blackTea.getSugarQuantity()+blackTea.getWastedSugarQuantity())));

	
			product.setWaterWasteAmount(product.getWaterWasteAmount() + quantity * blackTea.getWastedWaterQuantity());
			product.setSugarWasteAmount(product.getSugarWasteAmount() + quantity * blackTea.getWastedSugarQuantity());
			
		}else if (productType.equalsIgnoreCase("Coffee")) {
			product.setCoffeeContainerCapacity(product.getCoffeeContainerCapacity() - (quantity * (coffee.getCoffeeQuantity()+coffee.getWasteedCoffeeQuantity())));
			product.setMilkContainerCapacity(product.getMilkContainerCapacity() - (quantity * (coffee.getMilkQuantity()+coffee.getWastedMilkQuantity())));
			product.setSugarContainerCapacity(product.getSugarContainerCapacity() - (quantity * (coffee.getSugarQuantity()+coffee.getWastedSugarQuantity())));
			product.setWaterContainerCapacity(product.getWaterContainerCapacity() - (quantity * (coffee.getWaterQuantity()+coffee.getWastedWaterQuantity())));

			product.setCoffeeWasteAmount(product.getCoffeeWasteAmount() + quantity * 1);
			product.setMilkWasteAmount(product.getMilkWasteAmount() + quantity * 8);
			product.setSugarWasteAmount(product.getSugarWasteAmount() + quantity * 2);
			product.setWaterWasteAmount(product.getWaterWasteAmount() + quantity * 3);

		} else if (productType.equalsIgnoreCase("Black Coffee")) {
			product.setCoffeeContainerCapacity(product.getCoffeeContainerCapacity() - (quantity * blackCoffee.getCoffeeQuantity()+blackCoffee.getWasteedCoffeeQuantity() ));
			product.setSugarContainerCapacity(product.getSugarContainerCapacity() - (quantity * (blackCoffee.getSugarQuantity()+blackCoffee.getWastedSugarQuantity())));
			product.setWaterContainerCapacity(product.getWaterContainerCapacity() - (quantity * (blackCoffee.getWaterQuantity()+blackCoffee.getWastedWaterQuantity())));

			product.setSugarWasteAmount(product.getSugarWasteAmount() + quantity * 2);
			product.setWaterWasteAmount(product.getWaterWasteAmount() + quantity * 12);

		}
		

	}

	public void resetContainer(Product product) {

		product.setTeaContainerCapacity(product.getTeaContainerCapacity());
		product.setCoffeeContainerCapacity(product.getCoffeeContainerCapacity());
		product.setMilkContainerCapacity(product.getMilkContainerCapacity());
		product.setWaterContainerCapacity(product.getWaterContainerCapacity());
		product.setSugarContainerCapacity(product.getSugarContainerCapacity());
		System.out.println("All Containers successfully Reset");

	}

	
	public void checkContainerStatus(Product product) {

		System.out.println("*********Quantity Available in Containers*******");
		System.out.println("Tea:" + product.getTeaContainerCapacity());
		System.out.println("Coffee:" + product.getCoffeeContainerCapacity());
		System.out.println("Milk:" + product.getMilkContainerCapacity());
		System.out.println("Sugar:" + product.getSugarContainerCapacity());
		System.out.println("Water:" + product.getWaterContainerCapacity());

	}

	
	public void reFillContainer(Integer productID, Product product) {
		try {
			if (productID == 1 && product.getTeaContainerCapacity() < 2000) {
				product.setTeaContainerCapacity(2000);
				System.out.println("Successfully Refilled");
			} else if (productID == 2 && product.getCoffeeContainerCapacity() < 2000) {
				product.setCoffeeContainerCapacity(2000);
				System.out.println("Successfully Refilled");
			} else if (productID == 3 && product.getMilkContainerCapacity() < 10000) {
				product.setMilkContainerCapacity(10000);
				System.out.println("Successfully Refilled");
			} else if (productID == 4 && product.getWaterContainerCapacity() < 15000) {
				product.setWaterContainerCapacity(15000);
				System.out.println("Successfully Refilled");
			} else if (productID == 5 && product.getSugarContainerCapacity() < 8000) {
				product.setSugarContainerCapacity(8000);
				System.out.println("Successfully Refilled");
			} else {
				throw new RuntimeException();
			}
		} catch (RuntimeException e) {
			System.out.println("Container will Overflow!!");
		}

	}



}
