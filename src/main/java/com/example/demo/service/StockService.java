package com.example.demo.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.History;
import com.example.demo.entities.Holding;
import com.example.demo.entities.Stock;
import com.example.demo.repository.StockRepository;


@Service
public class StockService {
	
	@Autowired
	private StockRepository repository;
	
	public List<Stock> getAllstocks(){
		
		return repository.getAllStocks();
	}
	
	public Stock getStock(int id) {
		return repository.getStockById(id);
	}
	
	public Stock saveStock(Stock stock)
	{
		return repository.updateStock(stock);
	}
	public Stock newStock(Stock stock)
	{
		return repository.addStock(stock);
	}
	public int deleteStock(int id)
	{
		return repository.deleteStock(id);
	}

	public String BuyStock(String userName, String stockTicker, int volume) {
		System.out.println("service called");

		String a= repository.buyStock(userName ,stockTicker, volume);
		System.out.println(a);

		return a; // repository.buyStock(userName ,stockTicker, volume);
	}
	
	public String SellStock(String stockTicker, int volume,  String userName) {
		return repository.sellStock(stockTicker, volume, userName);
	}
	
	public List<Holding>  holdings(String userName) {
		return repository.holdings( userName);
	}
	
	public List<History> history(String userName) {
		return repository.history( userName);
	}
	
	public List<Map<String, Object>> dropDown1() {
		return repository.dropDown1();
	}
	
	public String getTicker(int id) {
		return repository.getTicker(id);
	}

	public String login(String username, String pwd) {
		// TODO Auto-generated method stub
		return repository.login(username,pwd);
	}

	public String register(String username, String pwd,String email) {
		// TODO Auto-generated method stub
		return repository.register(username,pwd,email);
	}
}
