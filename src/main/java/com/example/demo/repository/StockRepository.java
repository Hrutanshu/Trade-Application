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
	public String buyStock(String stockTicker, int volume);
	public String sellStock(String stockTicker, int volume);
	public List<Holding>  holdings();
	public List<History>  history();
	public List<Map<String, Object>> dropDown1();
	public String getTicker(int id);
}
