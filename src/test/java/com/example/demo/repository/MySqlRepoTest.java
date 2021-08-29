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
	MockMvc mockmvc;

	@Mock
	MySqlRepo mySqlRepo;	
	
	public List<Stock> datalist1 = new ArrayList<>();
	public Stock stock = new Stock();

	public List<History> datalist2 = new ArrayList<>();
	public History history = new History();
	
	@BeforeEach
	void setUp() throws Exception {
		
		stock.setCompany("Apple");
		stock.setId(1);
		stock.setPrice(45);
		stock.setStockTicker("AAPL");
		stock.setVolume(900);

		datalist1.add(stock);
		
		history.setDateTime("22/07/2021 12:12:12");
		history.setId(1);
		history.setPrice(45);
		history.setStockTicker("AAPL");
		history.setVolume(900);

		datalist2.add(history);


		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(mySqlRepo).build();
	}

	@Test
	void testGetAllStock() throws Exception
	{
		
		when(mySqlRepo.getAllStocks()).thenReturn(datalist1);
		assertEquals(datalist1,mySqlRepo.getAllStocks());
	}
	
	@Test
	public void testHistory() throws Exception {

		when(mySqlRepo.history()).thenReturn(datalist2);
		assertEquals(datalist2,mySqlRepo.history());
	}
	
	@Test
	public void testHoldings() throws Exception {

		List<Holding> datalist = new ArrayList<>();
		Holding holding = new Holding();
		holding.setId(1);
		holding.setPrice(45);
		holding.setStockTicker("AAPL");
		holding.setVolume(900);

		datalist.add(holding);

		when(mySqlRepo.holdings()).thenReturn(datalist);
		
		assertEquals(datalist,mySqlRepo.holdings());
	}
	
	@Test
	public void testGetStockByID() throws Exception {

		Stock stock = new Stock();
		stock.setCompany("Apple");
		stock.setId(1);
		stock.setPrice(45);
		stock.setStockTicker("AAPL");
		stock.setVolume(900);

		when(mySqlRepo.getStockById(1)).thenReturn(stock);
		assertThat(stock.getId()).isEqualTo(1);	 
	}

	@Test
	public void testAddStock() throws Exception {

		Stock stock = new Stock();
		stock.setCompany("Apple");
		//stock.setId(1);
		stock.setPrice(45);
		stock.setStockTicker("AAPL");
		stock.setVolume(900);

		when(mySqlRepo.addStock(stock)).thenReturn(stock);
		assertEquals(stock.toString(),mySqlRepo.addStock(stock).toString());
	
	}
	
	@Test
	public void testUpdateStock() throws Exception {

		Stock stock = new Stock();
		stock.setCompany("Apple");
		stock.setId(1);
		stock.setPrice(45);
		stock.setStockTicker("AAPL");
		stock.setVolume(900);

		when(mySqlRepo.updateStock(stock)).thenReturn(stock);
      	assertEquals(stock.toString(),mySqlRepo.updateStock(stock).toString());
	
	}
	
	@Test
	public void testDeleteStock() throws Exception {

		when(mySqlRepo.deleteStock(1)).thenReturn(1);
		assertThat(mySqlRepo.deleteStock(1)).isEqualTo(1);
	}
	
	@Test
	public void testBuyStock() throws Exception {

		String stockTicker="AAPL";
		int volume=10;
		String result="Bought Successfully";
		when(mySqlRepo.buyStock(stockTicker, volume)).thenReturn(result);
		assertEquals(mySqlRepo.buyStock(stockTicker,volume),result);
	
	}
	
	@Test
	public void testSellStock() throws Exception {

		String stockTicker="AAPL";
		int volume=10;
		String result="Sold Successfully";
		when(mySqlRepo.sellStock(stockTicker, volume)).thenReturn(result);

		assertEquals(mySqlRepo.sellStock(stockTicker,volume),result);
	
	}
	
	@Test
	public void testGetTicker() throws Exception {

		String ticker="AAPL";

		when(mySqlRepo.getTicker(1)).thenReturn(ticker);
		  assertEquals(ticker,mySqlRepo.getTicker(1));
	}
}
	
