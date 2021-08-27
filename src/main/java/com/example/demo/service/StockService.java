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

	public String BuyStock(String stockTicker, int volume) {
		return repository.buyStock(stockTicker, volume);
	}
	
	public String SellStock(String stockTicker, int volume) {
		return repository.sellStock(stockTicker, volume);
	}
	
	public List<Holding>  holdings() {
		return repository.holdings();
	}
	
	public List<History> history() {
		return repository.history();
	}
	
	public List<Map<String, Object>> dropDown1() {
		return repository.dropDown1();
	}
	
	public String getTicker(int id) {
		return repository.getTicker(id);
	}
}
