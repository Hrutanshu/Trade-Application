package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.entities.History;
import com.example.demo.entities.Holding;
import com.example.demo.entities.Stock;
import com.example.demo.service.StockService;


@ActiveProfiles("h2")
@SpringBootTest
@AutoConfigureMockMvc

public class MySqlRepoTest {
	
	
	@Autowired
	MySqlRepo mySqlRepo;
	List<Stock> datalist;
	
	public Stock stock1 = new Stock();
	
	@BeforeEach
	public void setUpData()
	{
		//1. Any setup stuff
		
		stock1.setCompany("Apple");
		stock1.setId(1);
		stock1.setPrice(45);
		stock1.setStockTicker("AAPL");
		stock1.setVolume(900);
		
	}	
	
	
	private void assertEquals(String string, String ticker) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void testGetAllStocks()
	{		
		//2. Call the method under test
		List <Stock> returnStock = mySqlRepo.getAllStocks();
		
		//3. Verify the results
		assertEquals(datalist, returnStock);
	}
	
	private void assertEquals(List<Stock> datalist, List<Stock> returnStock) {
		
	}
	
	@Test
	public void testHistory()
	{
		List<History> allHistory = mySqlRepo.history();
		assertThat(allHistory.size()).isGreaterThanOrEqualTo(1);
	}
	
	@Test
	public void testHoldings()
	{
		List<Holding> allHoldings = mySqlRepo.holdings();
		assertThat(allHoldings).isNotEmpty();
	}
	
}
	
