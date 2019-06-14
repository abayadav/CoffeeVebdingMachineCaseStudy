package com.yash.cvm.TeaCoffeeVendingMachine;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import com.yash.cvm.model.BlackCoffee;
import com.yash.cvm.model.BlackTea;
import com.yash.cvm.model.Coffee;
import com.yash.cvm.model.InputScanner;
import com.yash.cvm.model.Product;
import com.yash.cvm.model.Tea;
import com.yash.cvm.service.ContainerOperation;
import com.yash.cvm.service.GenerateReport;




@RunWith(MockitoJUnitRunner.class)
public class OrderTest {

	@InjectMocks
	private Order order;

	@Mock
	private Product product;
	

	@Mock
	private ContainerOperation containerOperations;

	/*@Mock
	private Payment payBill;*/

	@Mock
	private InputScanner inputScanner;

	@Mock
	private GenerateReport generateReportImpl;

	@Mock
	private Appender appenderMock;
	
	
	@Mock
	private Tea tea;
	
	@Mock
	private BlackTea blackTea;
	
	@Mock
	private Coffee coffee;
	
	@Mock
	private BlackCoffee blackCoffee;
	
	@Before 
	public void setupAppender(){
		
		Logger.getRootLogger().addAppender(appenderMock);
	}
	
	@After
	public void removeAppender(){
		Logger.getRootLogger().removeAppender(appenderMock);
	}
	
	
	@Test
	public void getMenuShouldAllowToOrderTea() {

		Product product1 = new Product();

		when(containerOperations.checkAvailabilty("Tea", 1, product1)).thenReturn(true);
		/*when(payBill.calculatePriceForOrder("Tea", 10.0, 1, 10)).thenReturn(0.0);*/
		doNothing().when(containerOperations).adjustContainerQuantity("Tea", 1, product1);
		when(inputScanner.nextInt()).thenReturn(1).thenReturn(1).thenReturn(10);
		
		when(tea.getTeaPrice()).thenReturn(10.0);
        
		order.startMenu(product1);
		assertTrue(containerOperations.checkAvailabilty("Tea", 1, product1));
		verify(appenderMock,times(5)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToOrderBlackTea() {

		Product product1 = new Product();

		when(containerOperations.checkAvailabilty("Black Tea", 1, product1)).thenReturn(true);
		/*when(payBill.calculatePriceForOrder("Black Tea", 5.0, 1, 5)).thenReturn(0.0);*/
		doNothing().when(containerOperations).adjustContainerQuantity("Black Tea", 1, product1);
		when(inputScanner.nextInt()).thenReturn(2).thenReturn(1).thenReturn(5);

		when(blackTea.getBlackTeaPrice()).thenReturn(5.0);
		
		order.startMenu(product1);
        assertTrue(containerOperations.checkAvailabilty("Black Tea", 1, product1));
		verify(appenderMock,times(5)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToOrderCoffee() {

		Product product1 = new Product();

		when(containerOperations.checkAvailabilty("Coffee", 1, product1)).thenReturn(true);
		/*when(payBill.calculatePriceForOrder("Coffee", 15.0, 1, 15)).thenReturn(0.0);*/
		doNothing().when(containerOperations).adjustContainerQuantity("Coffee", 1, product1);
		when(inputScanner.nextInt()).thenReturn(3).thenReturn(1).thenReturn(15);

		when(coffee.getCoffeePrice()).thenReturn(15.0);
		order.startMenu(product1);

		assertTrue(containerOperations.checkAvailabilty("Coffee", 1, product1));
		verify(appenderMock,times(5)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToOrderBlackCoffee() {

		Product product1 = new Product();

		when(containerOperations.checkAvailabilty("Black Coffee", 1, product1)).thenReturn(true);
		/*when(payBill.calculatePriceForOrder("Black Coffee", 10.0, 1, 10)).thenReturn(0.0);*/
		doNothing().when(containerOperations).adjustContainerQuantity("Black Coffee", 1, product1);
		when(inputScanner.nextInt()).thenReturn(4).thenReturn(1).thenReturn(10);

		when(blackCoffee.getCoffeePrice()).thenReturn(10.0);
		order.startMenu(product1);

		assertTrue(containerOperations.checkAvailabilty("Black Coffee", 1, product1));
		verify(appenderMock,times(5)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToOrderResetContainers() {

		Product product1 = new Product();

		doNothing().when(containerOperations).resetContainer(product1);
		when(inputScanner.nextInt()).thenReturn(5).thenReturn(1);

		order.startMenu(product1);

		verify(containerOperations).resetContainer(product1);
		verify(appenderMock,times(3)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToOrderRefillContainers() {

		Product product1 = new Product();

		product1.setTeaContainerCapacity(1000);

		doNothing().when(containerOperations).reFillContainer(1, product1);
		when(inputScanner.nextInt()).thenReturn(6).thenReturn(1).thenReturn(1);

		order.startMenu(product1);

		verify(containerOperations).reFillContainer(1, product1);
		verify(appenderMock,times(5)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToCheckContainersStatus() {

		Product product1 = new Product();

		doNothing().when(containerOperations).checkContainerStatus(product1);
		when(inputScanner.nextInt()).thenReturn(7).thenReturn(1);

		order.startMenu(product1);

		verify(containerOperations).checkContainerStatus(product1);
		verify(appenderMock,times(3)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToGenerateReport() {

		Product product1 = new Product();

		HashMap<String, List> totalSalePercontainer = new HashMap<String, List>();

		doNothing().when(generateReportImpl).prepareReport(product1, totalSalePercontainer, 0, 0.0);
		when(inputScanner.nextInt()).thenReturn(8).thenReturn(1);

		order.startMenu(product1);
		
		verify(appenderMock,times(4)).doAppend(Mockito.anyObject());
	}

	@Test 
	public void getMenuShouldHandleExceptionWhenAvalabilityIsFalse() {

		Product product1 = new Product();

		when(containerOperations.checkAvailabilty("Tea", 10, product1)).thenReturn(false);

		when(inputScanner.nextInt()).thenReturn(1).thenReturn(10).thenReturn(1);

		order.startMenu(product1);

		assertFalse(containerOperations.checkAvailabilty("Tea", 10, product1));
		
		verify(appenderMock,times(5)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAskForMenuWhenInputIsInvalid() {

		Product product1 = new Product();

		when(inputScanner.nextInt()).thenReturn(10).thenReturn(1);

		order.startMenu(product1);
		
		verify(appenderMock, times(4)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldReturnExtraAmountWhenInserted() {

		Product product1 = new Product();
		
		when(inputScanner.nextInt()).thenReturn(1).thenReturn(1).thenReturn(12).thenReturn(1);
		when(containerOperations.checkAvailabilty("Tea", 1, product1)).thenReturn(true);
		/*when(payBill.calculatePriceForOrder("Tea", 10.0, 1, 12)).thenReturn(2.0);*/
		doNothing().when(containerOperations).adjustContainerQuantity("Tea", 1, product1);
		
		order.startMenu(product1);
		verify(appenderMock,times(5)).doAppend(Mockito.anyObject());
	}
	

	
	@Test
	public void shouldExitFromSystemorder(){
		
		Product product1 = new Product();
		when(inputScanner.nextInt()).thenReturn(9);
		
		order.startMenu(product1);
		
		verify(appenderMock,times(4)).doAppend(Mockito.anyObject());
		
		
	}

}
