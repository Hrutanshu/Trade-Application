package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	
	
}
