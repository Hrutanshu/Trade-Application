package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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
import com.example.demo.repository.StockRepository;

@ActiveProfiles("h2")
@SpringBootTest
@AutoConfigureMockMvc
class TestStockService {

	
	@Autowired
	MockMvc mockmvc;

	@InjectMocks
	StockService stockService;

	@Mock
	StockRepository repository;

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
		mockmvc = MockMvcBuilders.standaloneSetup(stockService).build();
	}
	
	@Test
	void testGetAllStock() throws Exception
	{
		
		when(stockService.getAllstocks()).thenReturn(datalist1);
		assertEquals(datalist1,stockService.getAllstocks());
	}
	
	@Test
	public void testHistory() throws Exception {

		when(stockService.history("user")).thenReturn(datalist2);
		assertEquals(datalist2,stockService.history("user"));
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

		when(stockService.holdings("user")).thenReturn(datalist);
		
		assertEquals(datalist,stockService.holdings("user"));
	}
	@Test
	public void testgetByID() throws Exception {

		Stock stock = new Stock();
		stock.setCompany("Apple");
		stock.setId(1);
		stock.setPrice(45);
		stock.setStockTicker("AAPL");
		stock.setVolume(900);

		when(stockService.getStock(1)).thenReturn(stock);

		
		  assertThat(stock.getId()).isEqualTo(1);	 
	}
	
	@Test
	public void testGetTicker() throws Exception {

		String ticker="AAPL";

		when(stockService.getTicker(1)).thenReturn(ticker);
		  assertEquals(ticker,stockService.getTicker(1));
	}
	
	@Test
	public void testAddStock() throws Exception {

		Stock stock = new Stock();
		stock.setCompany("Apple");
		stock.setId(1);
		stock.setPrice(45);
		stock.setStockTicker("AAPL");
		stock.setVolume(900);

		when(stockService.newStock(stock)).thenReturn(stock);
		  assertEquals(stock.toString(),stockService.newStock(stock).toString());
	
	}
	
	@Test
	public void testSellStock() throws Exception {

		String stockTicker="AAPL";
		int volume=10;
		String result="Sold Successfully";
		String user="user";
		when(stockService.SellStock(stockTicker, volume, user)).thenReturn(result);

		assertEquals(stockService.SellStock(stockTicker,volume, user),result);
	
	}
	

	@Test
	public void testBuyStock() throws Exception {

		String stockTicker="AAPL";
		int volume=10;
		String result="Bought Successfully";
		String user="user";

		when(stockService.BuyStock(user, stockTicker, volume)).thenReturn(result);
		assertEquals(stockService.BuyStock(user ,stockTicker,volume),result);
	
	}
	

	@Test
	public void testLogin() throws Exception
	{
		when(stockService.login("richa", "richa")).thenReturn("1");
		assertEquals(stockService.login("richa", "richa"),"1");
	}
	

	@Test
	public void testRegister() throws Exception
	{
		String ans="1";
		String name="richa";
		String pwd="richa";
		String mail="richa";
		when(stockService.register(name,mail,pwd)).thenReturn(ans);
		assertEquals(stockService.register(name,mail,pwd),ans);
	}
	
	
	@Test
	public void testEditStock() throws Exception {

		Stock stock = new Stock();
		stock.setCompany("Apple");
		stock.setId(1);
		stock.setPrice(45);
		stock.setStockTicker("AAPL");
		stock.setVolume(900);

		when(stockService.saveStock(stock)).thenReturn(stock);
      	  assertEquals(stock.toString(),stockService.saveStock(stock).toString());
	
	}
	
	@Test
	public void testDeleteStock() throws Exception {

		when(stockService.deleteStock(1)).thenReturn(1);

		assertThat(stockService.deleteStock(1)).isEqualTo(1);
	}


}
