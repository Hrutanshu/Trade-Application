package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.entities.History;
import com.example.demo.entities.Holding;
import com.example.demo.entities.Stock;

@SpringBootTest
@ActiveProfiles("h2")
public class MySqlRepoTest {
	
	
	@Autowired
	StockRepository stockRepo;
	
	@Test
	public void testGetAllStocks()
	{
		List <Stock> stocks = stockRepo.getAllStocks();
		assertThat(stocks.size()>0);
	}
	
	@Test
	public void testAddStock()
	{
		Stock stock = stockRepo.addStock(new Stock(1,"Apple","AAPL",45,900));
		assertThat(stock.getId()).isGreaterThan(0);
	}
	/*
	@Test
	public void testHistory()
	{
		String user="hrish";
		List<History> allHistory = stockRepo.history(user);
		//assertThat(allHistory.size()).isGreaterThan(0);
		assertThat(allHistory).isNotNull();
	}
	
	@Test
	public void testHoldings()
	{
		List<Holding> datalist = new ArrayList<>();
		Holding h=new Holding();
		h.setId(1);
		h.setPrice(12);
		h.setStockTicker("AAP");
		h.setVolume(444);

		datalist.add(h);
		String user="hrish";

		//List<Holding> allHoldings = stockRepo.holdings(user);
		when(stockRepo.holdings(user)).thenReturn(datalist);

		assertThat(stockRepo.holdings(user).size()).isGreaterThan(0);
	}*/
	
	
	private void assertEquals(String buyStock, String result) {
		// TODO Auto-generated method stub
		
	}

	private void assertEquals(List<Holding> holdings, List<Holding> datalist) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void testDeleteStock()
	{
		Stock stock = stockRepo.getStockById(2);
		stockRepo.deleteStock(stock.getId());
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
				stockRepo.getStockById(2); });
	}
}
	
