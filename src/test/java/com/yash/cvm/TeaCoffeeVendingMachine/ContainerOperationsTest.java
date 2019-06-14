package com.yash.cvm.TeaCoffeeVendingMachine;


import static org.mockito.Mockito.when;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import com.yash.cvm.model.Product;
import com.yash.cvm.service.ContainerOperation;

@RunWith(MockitoJUnitRunner.class)
public class ContainerOperationsTest {

	@InjectMocks
	private ContainerOperation containerOperations;

	@Mock
	private Product container;

	@Test
	public void adjustContainerQuantityShouldUpdateQuantitiesWhenOrderIsTea() {

		Product containerForTea = new Product();

		containerOperations.adjustContainerQuantity("Tea", 1, containerForTea);

		assertEquals((Integer) 1, containerForTea.getTeaWasteAmount());
		assertEquals((Integer) 4, containerForTea.getMilkWasteAmount());
		assertEquals((Integer) 5, containerForTea.getWaterWasteAmount());
		assertEquals((Integer) 2, containerForTea.getSugarWasteAmount());

	}

	@Test
	public void adjustContainerQuantityShouldUpdateQuantitiesWhenOrderIsBlackTea() {

		Product containerForBlackTea = new Product();

		containerOperations.adjustContainerQuantity("Black Tea", 1, containerForBlackTea);

		assertEquals((Integer) 12, containerForBlackTea.getWaterWasteAmount());
		assertEquals((Integer) 2, containerForBlackTea.getSugarWasteAmount());

	}

	@Test
	public void adjustContainerQuantityShouldUpdateQuantitiesWhenOrderIsCoffee() {

		Product containerForCoffee = new Product();

		containerOperations.adjustContainerQuantity("Coffee", 1, containerForCoffee);

		assertEquals((Integer) 1, containerForCoffee.getCoffeeWasteAmount());
		assertEquals((Integer) 3, containerForCoffee.getWaterWasteAmount());
		assertEquals((Integer) 8, containerForCoffee.getMilkWasteAmount());
		assertEquals((Integer) 2, containerForCoffee.getSugarWasteAmount());

	}
	
	@Test
	public void adjustContainerQuantityShouldUpdateQuantitiesWhenOrderIsNull() {

		Product containerForNull = new Product();

		containerOperations.adjustContainerQuantity("", 1, containerForNull);

		//doNothing().when(containerOperations).adjustContainerQuantity("", 1, containerForNull);

	}

	@Test
	public void adjustContainerQuantityShouldUpdateQuantitiesWhenOrderIsBlackCoffee() {

		Product containerForBlackCoffee = new Product();

		containerOperations.adjustContainerQuantity("Black Coffee", 1, containerForBlackCoffee);

		assertEquals((Integer) 12, containerForBlackCoffee.getWaterWasteAmount());
		assertEquals((Integer) 2, containerForBlackCoffee.getSugarWasteAmount());

	}

	@Test
	public void shouldRefillTeaContainer() {

		when(container.getTeaContainerCapacity()).thenReturn(1200);

		containerOperations.reFillContainer(1, container);

	}

	@Test
	public void shouldRefillCoffeeContainer() {

		when(container.getCoffeeContainerCapacity()).thenReturn(1200);

		containerOperations.reFillContainer(2, container);

	}

	@Test
	public void shouldRefillMilkContainer() {

		when(container.getMilkContainerCapacity()).thenReturn(100);

		containerOperations.reFillContainer(3, container);

	}

	@Test
	public void shouldRefillWaterContainer() {

		when(container.getWaterContainerCapacity()).thenReturn(100);

		containerOperations.reFillContainer(4, container);

	}

	@Test
	public void shouldRefillSugarContainer() {

		when(container.getSugarContainerCapacity()).thenReturn(100);

		containerOperations.reFillContainer(5, container);
	}

	@Test
	public void shouldCheckContainerStatus() {

		when(container.getTeaContainerCapacity()).thenReturn(2000);
		when(container.getCoffeeContainerCapacity()).thenReturn(1200);
		when(container.getWaterContainerCapacity()).thenReturn(14000);
		when(container.getSugarContainerCapacity()).thenReturn(7000);
		when(container.getMilkContainerCapacity()).thenReturn(10000);

		containerOperations.checkContainerStatus(container);

	}

	@Test
	public void shouldResetContainers() {

		containerOperations.resetContainer(container);

	}

	@Test
	public void shouldReturnTrueForCheckAvailabilityWhenOrderIsTea() {

		when(container.getTeaContainerCapacity()).thenReturn(2000);
		when(container.getMilkContainerCapacity()).thenReturn(10000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		assertTrue(containerOperations.checkAvailabilty("Tea", 1, container));

	}

	@Test
	public void shouldReturnTrueForCheckAvailabilityWhenOrderIsBlackTea() {

		when(container.getTeaContainerCapacity()).thenReturn(2000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		assertTrue(containerOperations.checkAvailabilty("Black Tea", 1, container));

	}

	@Test
	public void shouldReturnTrueForCheckAvailabilityWhenOrderIsCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(2000);
		when(container.getMilkContainerCapacity()).thenReturn(10000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		assertTrue(containerOperations.checkAvailabilty("Coffee", 1, container));

	}

	@Test
	public void shouldReturnTrueForCheckAvailabilityWhenOrderIsBlackCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(2000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		assertTrue(containerOperations.checkAvailabilty("Black Coffee", 1, container));

	}

	@Test
	public void shouldReturnFalseForCheckAvailabilityWhenTeaContainerContainsLessThanRequiredForOrderTea() {

		when(container.getTeaContainerCapacity()).thenReturn(1);
		when(container.getMilkContainerCapacity()).thenReturn(10000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		assertFalse(containerOperations.checkAvailabilty("Tea", 1, container));

	}

	@Test
	public void shouldReturnFalseForCheckAvailabilityWhenMilkContainerContainsLessThanRequiredForOrderTea() {

		when(container.getTeaContainerCapacity()).thenReturn(2000);
		when(container.getMilkContainerCapacity()).thenReturn(1);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabilty("Tea", 1, container);

		assertFalse(containerOperations.checkAvailabilty("Tea", 1, container));
	}

	@Test
	public void shouldReturnFalseForCheckAvailabilityWhenWaterContainerContainsLessThanRequiredForOrderTea() {

		when(container.getTeaContainerCapacity()).thenReturn(2000);
		when(container.getMilkContainerCapacity()).thenReturn(10000);
		when(container.getWaterContainerCapacity()).thenReturn(2);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabilty("Tea", 1, container);

		assertFalse(containerOperations.checkAvailabilty("Tea", 1, container));
	}

	@Test
	public void shouldReturnFalseForCheckAvailabilityWhenSugarContainerContainsLessThanRequiredForOrderTea() {

		when(container.getTeaContainerCapacity()).thenReturn(2000);
		when(container.getMilkContainerCapacity()).thenReturn(10000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(3);

		containerOperations.checkAvailabilty("Tea", 1, container);

		assertFalse(containerOperations.checkAvailabilty("Tea", 1, container));
	}

	@Test
	public void shouldReturnFalseForCheckAvailabilityWhenTeaContainerContainsLessThanRequiredForOrderBlackTea() {

		when(container.getTeaContainerCapacity()).thenReturn(1);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabilty("Black Tea", 1, container);

		assertFalse(containerOperations.checkAvailabilty("Black Tea", 1, container));

	}

	@Test
	public void shouldReturnFalseForCheckAvailabilityWhenWaterContainerContainsLessThanRequiredForOrderBlackTea() {

		when(container.getTeaContainerCapacity()).thenReturn(2000);
		when(container.getWaterContainerCapacity()).thenReturn(1);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabilty("Black Tea", 1, container);

		assertFalse(containerOperations.checkAvailabilty("Black Tea", 1, container));

	}

	@Test
	public void shouldReturnFalseForCheckAvailabilityWhenSugarContainerContainsLessThanRequiredForOrderBlackTea() {

		when(container.getTeaContainerCapacity()).thenReturn(2000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(1);

		containerOperations.checkAvailabilty("Black Tea", 1, container);

		assertFalse(containerOperations.checkAvailabilty("Black Tea", 1, container));
	}

	@Test
	public void shouldReturnFalseWhenCoffeeContainerContainsLessThanRequiredForOrderCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(2);
		when(container.getMilkContainerCapacity()).thenReturn(10000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabilty("Coffee", 1, container);

		assertFalse(containerOperations.checkAvailabilty("Coffee", 1, container));

	}

	@Test
	public void shouldReturnFalseWhenWaterContainerContainsLessThanRequiredForOrderCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(2000);
		when(container.getMilkContainerCapacity()).thenReturn(10000);
		when(container.getWaterContainerCapacity()).thenReturn(1);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabilty("Coffee", 1, container);

		assertFalse(containerOperations.checkAvailabilty("Coffee", 1, container));
	}

	@Test
	public void shouldReturnFalseWhenMilkContainerContainsLessThanRequiredForOrderCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(2000);
		when(container.getMilkContainerCapacity()).thenReturn(4);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabilty("Coffee", 1, container);

		assertFalse(containerOperations.checkAvailabilty("Coffee", 1, container));

	}

	@Test
	public void shouldReturnFalseWhenSugarContainerContainsLessThanRequiredForOrderCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(2000);
		when(container.getMilkContainerCapacity()).thenReturn(10000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(5);

		containerOperations.checkAvailabilty("Coffee", 1, container);

		assertFalse(containerOperations.checkAvailabilty("Coffee", 1, container));

	}

	@Test
	public void shouldReturnFalseWhenCoffeeContainerContainsLessThanRequiredForOrderBlackCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(1);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabilty("Black Coffee", 1, container);

		assertFalse(containerOperations.checkAvailabilty("Black Coffee", 1, container));

	}

	@Test
	public void shouldReturnFalseWhenWaterContainerContainsLessThanRequiredForOrderBlackCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(2000);
		when(container.getWaterContainerCapacity()).thenReturn(2);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabilty("Black Coffee", 1, container);

		assertFalse(containerOperations.checkAvailabilty("Black Coffee", 1, container));

	}

	@Test
	public void shouldReturnFalseWhenSugarContainerContainsLessThanRequiredForOrderBlackCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(2000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(2);

		containerOperations.checkAvailabilty("Black Coffee", 1, container);

		assertFalse(containerOperations.checkAvailabilty("Black Coffee", 1, container));

	}

	@Test
	public void shouldReturnFalseWhencontainerTypeIsUnknown() {

		containerOperations.checkAvailabilty("Pepsi", 1, container);

		assertFalse(containerOperations.checkAvailabilty("Pepsi", 1, container));

	}

	@Test
	public void reFillContainershouldHandleExceptionWhenTeaContainerIsFull() {

		Product container = new Product();
		container.setTeaContainerCapacity(2000);

		containerOperations.reFillContainer(1, container);

	}

	@Test
	public void reFillContainershouldHandleExceptionWhenCoffeeContainerIsFull() {

		Product container = new Product();
		container.setCoffeeContainerCapacity(2000);

		containerOperations.reFillContainer(2, container);

	}

	@Test
	public void reFillContainershouldHandleExceptionWhenMilkContainerIsFull() {

		Product container = new Product();
		container.setCoffeeContainerCapacity(10000);

		containerOperations.reFillContainer(3, container);

	}

	@Test
	public void reFillContainershouldHandleExceptionWhenWaterContainerIsFull() {

		Product container = new Product();
		container.setCoffeeContainerCapacity(15000);

		containerOperations.reFillContainer(4, container);

	}

	@Test
	public void reFillContainershouldHandleExceptionWhenSugarContainerIsFull() {

		Product container = new Product();
		container.setCoffeeContainerCapacity(8000);

		containerOperations.reFillContainer(5, container);

	}

}
