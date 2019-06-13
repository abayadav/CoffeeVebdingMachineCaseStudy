package com.yash.cvm.TeaCoffeeVendingMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.yash.cvm.model.BlackTea;
import com.yash.cvm.model.InputScanner;
import com.yash.cvm.model.Product;
import com.yash.cvm.model.Tea;
import com.yash.cvm.model.TotalSaleCost;
import com.yash.cvm.service.impl.ContainerOperationsImpl;
import com.yash.cvm.service.impl.GenerateReportImpl;
import com.yash.cvm.service.impl.PaymentImpl;

public class Order {
	
	private final static Logger logger = Logger.getLogger(Order.class);
	
	
	private Integer totalQuantitySold = 0;
    private Double total = 0.0;
    private Integer insertedAmount = 0;
    private Double returnedAmount = 0.0;
    private Integer refillOption = 0;
    
    HashMap<String, List> totalSalePercontainer = new HashMap<String, List>();
    List<TotalSaleCost> totalSaleCostList = new ArrayList<TotalSaleCost>();
	
    InputScanner inputScanner = new InputScanner();
    ContainerOperationsImpl containerOperations = new ContainerOperationsImpl();
    GenerateReportImpl generateReport = new GenerateReportImpl();
	PaymentImpl payBill = new PaymentImpl();

	
    Tea tea=new Tea();
    BlackTea blackTea=new BlackTea();
    
    public Order(){
    	
    }
    
    public Order(ContainerOperationsImpl containerOperations, InputScanner inputScanner, Tea tea) {
		super();
		this.containerOperations = containerOperations;
		this.generateReport = generateReport;
		this.payBill = payBill;
		this.inputScanner = inputScanner;
		this.tea=tea;
		this.blackTea=blackTea;
	}
   
	
	public void startMenu(Product product) {
		
		logger.info("Welcome");
		
		logger.info("1.Tea \n2.Black Tea \n3.Coffee \n4.Black Coffee \n5.Reset Container \n6.Refill Container \n7.Check Container Status \n8.Report \n9.Exit");
		
		int askUser;
		
		do {
			int quantityOfOrder = 0;
			System.out.println("Please enter the order number");
			int selectedOrder = inputScanner.nextInt();
			
			if(selectedOrder<=4){
				System.out.println("Please enter the quantity required");
				quantityOfOrder= inputScanner.nextInt();
			}
			
			switch (selectedOrder) {
			case 1:
				logger.info("You have ordered " + quantityOfOrder + " tea");
				prepareOrder("Tea",tea.getTeaPrice(), quantityOfOrder, product);
				break;
	
				
			case 2:
				
				logger.info("You have ordered " + quantityOfOrder + " black tea");
				prepareOrder("Black Tea",blackTea.getTeaPrice(), quantityOfOrder, product);
				
				break;

			case 3:
				
				logger.info("You have ordered " + quantityOfOrder + " coffee");
				prepareOrder("Coffee", 15.0, quantityOfOrder, product);
				break;
			
			case 4:
				//System.out.println("You have ordered " + quantityOfOrder + " black coffee");
				logger.info("You have ordered " + quantityOfOrder + " black coffee");
				prepareOrder("Black Coffee", 10.0, quantityOfOrder, product);
				break;
		
			case 5:
				containerOperations.resetContainer(product);
				break;
			
			case 6:
				
				logger.info("****Refill Container****");
				logger.info("1.Tea\n2.Coffee\n3.Milk\n4.Sugar\n5.Water");
				refillOption = inputScanner.nextInt();
				containerOperations.reFillContainer(refillOption, product);
				break;
			
			case 7:
				containerOperations.checkContainerStatus(product);
				break;
			
			case 8:
				
				logger.info("################Report################");
				generateReport.prepareReport(product, totalSalePercontainer, totalQuantitySold, total);
				break;
			
			case 9:
				
				logger.info("Thank you for your visit");
				break;

			default:
				
				logger.warn("You have entered invalid option!");
				break;
			}
			

			logger.info("Enter 0 for Menu Or 1 to Exit:");
			askUser = inputScanner.nextInt();
		} while (askUser==0);
		
	}



	public void prepareOrder(String orderType, double costPerCup, int orderQuantity, Product product) {
		try {
			
			if (containerOperations.checkAvailabilty(orderType, orderQuantity, product)) {
				int amount_flag = 0;
				do {
					System.out.println("Please enter " + costPerCup * orderQuantity + " Rupee:");

					insertedAmount = inputScanner.nextInt();
					
					if (insertedAmount >= costPerCup * orderQuantity) {
						amount_flag = 1;
						returnedAmount = payBill.calculatePriceForOrder(orderType, costPerCup, orderQuantity,
								insertedAmount);

						containerOperations.adjustContainerQuantity(orderType, orderQuantity, product);

						
						logger.info("Drink successfully served using logger");
						
						if (Math.abs(returnedAmount) > 0.0)
							
							logger.info("Please collect your change:" + Math.abs(returnedAmount));
						
						totalQuantitySold = totalQuantitySold + orderQuantity;
						total = total + costPerCup * orderQuantity;

						for (TotalSaleCost totalCost : totalSaleCostList) {
							if (totalCost.getProductName().equals(orderType)) {
								TotalSaleCost totalSaleCost = new TotalSaleCost();
								totalSaleCost.setCost(totalCost.getCost() + orderQuantity * costPerCup);
								totalSaleCost.setQuantity(totalCost.getQuantity() + orderQuantity);
								totalSaleCost.setProductName(totalCost.getProductName());
								totalSaleCost.setProductID(totalCost.getProductID());

								totalSaleCostList.set(totalSaleCostList.indexOf(totalCost), totalSaleCost);

							}

						}

						totalSalePercontainer.put("total_Sale_Cost", totalSaleCostList);
					}
				} while (amount_flag == 0);

			} else {
				throw new RuntimeException();
			}
		}

		catch (RuntimeException e) {
			
			logger.error("Requested quantity not available, Please try again!");
			
			

		}

	}


}
