package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entities.History;
import com.example.demo.entities.Holding;
import com.example.demo.entities.Stock;
import com.example.demo.service.StockService;
import com.fasterxml.jackson.core.type.TypeReference;

@ActiveProfiles("h2")
@SpringBootTest
@AutoConfigureMockMvc

class TestStockController {


	@Autowired
	MockMvc mockmvc;

	@InjectMocks
	StockController stockController;

	@Mock
	StockService stockService;

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(stockController).build();
	}
	
	@Test
	public void testLogin() throws Exception
	{
		when(stockController.login("richa", "richa")).thenReturn("1");
		assertEquals(stockController.login("richa", "richa"),"1");
	}
	

	@Test
	public void testRegister() throws Exception
	{
		when(stockController.register("richa","richa", "richa")).thenReturn("1");
		assertEquals(stockController.register("richa", "richa" ,"richa"),"1");
	}
	
	@Test
	public void testAllStock() throws Exception {

		List<Stock> datalist = new ArrayList<>();
		Stock stock = new Stock();
		stock.setCompany("Apple");
		stock.setId(1);
		stock.setPrice(45);
		stock.setStockTicker("AAPL");
		stock.setVolume(900);

		datalist.add(stock);

		when(stockController.getAllStock()).thenReturn(datalist);

		String URI = "/api/stock/";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJsonStock(datalist);
		String outputInJson = result.getResponse().getContentAsString();

		assertEquals(outputInJson, expectedJson);

	}
	
	@Test
	public void testHistory() throws Exception {

		List<History> datalist = new ArrayList<>();
		History history = new History();
		history.setDateTime("22/07/2021 12:12:12");
		history.setId(1);
		history.setPrice(45);
		history.setStockTicker("AAPL");
		history.setVolume(900);

		datalist.add(history);
		String user="user";

		when(stockController.history(user)).thenReturn(datalist);

		String URI = "/api/stock/history/user";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(datalist);
		String outputInJson = result.getResponse().getContentAsString();

		assertEquals(outputInJson, expectedJson);

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
		String user="user";

		when(stockController.Holdings(user)).thenReturn(datalist);

		String URI = "/api/stock/holdings/user";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJsonHolding(datalist);
		String outputInJson = result.getResponse().getContentAsString();

		assertEquals(outputInJson, expectedJson);

	}


	@Test
	public void testgetByID() throws Exception {

		Stock stock = new Stock();
		stock.setCompany("Apple");
		stock.setId(1);
		stock.setPrice(45);
		stock.setStockTicker("AAPL");
		stock.setVolume(900);

		when(stockController.getStockById(1)).thenReturn(stock);

		assertEquals(stockController.getStockById(1),stock);	 
	}
	
	@Test
	public void testGetTicker() throws Exception {

		String ticker="AAPL";

		when(stockController.getTicker(1)).thenReturn(ticker);
		  assertEquals(ticker,stockController.getTicker(1));
	}
	
	@Test
	public void testAddStock() throws Exception {

		Stock stock = new Stock();
		stock.setCompany("Apple");
		stock.setId(1);
		stock.setPrice(45);
		stock.setStockTicker("AAPL");
		stock.setVolume(900);

		when(stockController.addStock(stock)).thenReturn(stock);
		  assertEquals(stock.toString(),stockController.addStock(stock).toString());
	
	}
	
	@Test
	public void testSellStock() throws Exception {

		String stockTicker="AAPL";
		int volume=10;

		String url="/api/stock/sell/AAPL/10";
		String result="Sold Successfully";
		String user="user";

		when(stockController.SellStock(stockTicker, volume,user)).thenReturn(result);

		assertEquals(stockController.SellStock(stockTicker,volume,user),result);
	
	}
	
	@Test
	public void testBuyStock() throws Exception {

		String stockTicker="AAPL";
		int volume=10;

		String url="/api/stock/buy/AAPL/10";
		String result="Bought Successfully";
		String user="user";

		when(stockController.BuyStock(user, stockTicker, volume)).thenReturn(result);
		assertEquals(stockController.BuyStock(user,stockTicker,volume),result);
	
	}
	
	@Test
	public void testEditStock() throws Exception {

		Stock stock = new Stock();
		stock.setCompany("Apple");
		stock.setId(1);
		stock.setPrice(45);
		stock.setStockTicker("AAPL");
		stock.setVolume(900);

		when(stockController.editStock(stock)).thenReturn(stock);
      	  assertEquals(stock.toString(),stockController.editStock(stock).toString());
	
	}
	
	@Test
	public void testDeleteStock() throws Exception {

		when(stockController.deleteStock(1)).thenReturn(1);

		assertThat(stockController.deleteStock(1)).isEqualTo(1);
	}
	
	private String mapToJson(List<History> object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	private String mapToJsonStock(List<Stock> object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	

	private String mapToJsonHolding(List<Holding> object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
