package com.yash.cvm.TeaCoffeeVendingMachine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import com.yash.cvm.TeaCoffeeVendingMachine.Order;
import com.yash.cvm.TeaCoffeeVendingMachine.TeaCoffeeVendingMachine;

@RunWith(MockitoJUnitRunner.class)
public class TeaCoffeeVendingMachineTest {

	@InjectMocks
	private TeaCoffeeVendingMachine teaCoffeeVendingMachine;

	@Mock
	private static com.yash.cvm.model.Product product;

	@Mock
	private static Order Order;

	@Test
	public void mainTest() {
	
		
	//	Mockito.doNothing().when(customerOrder).getMenu(product);
		
	//	TeaCoffeeVendingMachine.main(null);
		

	}

}
