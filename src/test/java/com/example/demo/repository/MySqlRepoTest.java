package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
	
	@Test
	public void testHistory()
	{
		List<History> allHistory = stockRepo.history();
		//assertThat(allHistory.size()).isGreaterThan(0);
		assertThat(allHistory).isNotNull();
	}
	
	@Test
	public void testHoldings()
	{
		List<Holding> allHoldings = stockRepo.holdings();
		//assertThat(allHoldings).isNotEmpty();
		assertThat(allHoldings).isNotNull();
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
	
