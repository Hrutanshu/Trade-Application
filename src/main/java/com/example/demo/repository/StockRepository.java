package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import com.example.demo.entities.History;
import com.example.demo.entities.Holding;
import com.example.demo.entities.Stock;

public interface StockRepository {
	
	public List<Stock> getAllStocks();
	public Stock getStockById(int id);
	public Stock updateStock(Stock stock);
	public int deleteStock(int id);
	public Stock addStock(Stock stock);
	public String buyStock(String userName, String stockTicker, int volume);
	public String sellStock(String stockTicker, int volume, String userName);
	public List<Holding>  holdings(String userName);
	public List<History>  history(String userName);
	public List<Map<String, Object>> dropDown1();
	public String getTicker(int id);
	public String login(String username, String pwd);
	public String register(String username, String pwd, String email);
}
