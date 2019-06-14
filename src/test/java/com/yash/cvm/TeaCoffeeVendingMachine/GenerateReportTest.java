package com.yash.cvm.TeaCoffeeVendingMachine;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.cvm.model.Product;
import com.yash.cvm.model.TotalSaleCost;
import com.yash.cvm.service.ContainerOperation;
import com.yash.cvm.service.GenerateReport;

@RunWith(MockitoJUnitRunner.class)
public class GenerateReportTest {

	@InjectMocks
	private GenerateReport generateReport;

	@Mock
	private Product product;

	@Mock
	private TotalSaleCost totalSaleCost;
	
	@Mock
	private ContainerOperation containerOperation;

	@Test
	public void shouldGenerateReport() {

		GenerateReport report = Mockito.mock(GenerateReport.class);
		
		//Product p1=new Product();
		
		
		//doNothing().when(containerOperation).checkContainerStatus(product);
		
		TotalSaleCost totalCost = new TotalSaleCost();
		totalCost.setProductID(1);
		totalCost.setProductName("Tea");
		totalCost.setQuantity(5);
		totalCost.setCost(10d);
		
		List<TotalSaleCost> totalSaleCostList = new ArrayList<TotalSaleCost>();
		totalSaleCostList.add(totalCost);
		
		HashMap<String, List> totalSale = new HashMap<String, List>();		
		
		totalSale.put("total_Sale_Cost", totalSaleCostList);


		doCallRealMethod().when(report).prepareReport(product, totalSale,10,10.0);

		report.prepareReport(product, totalSale,10,10.0);

		verify(report).prepareReport(product, totalSale,10,10.0);

	}

}
