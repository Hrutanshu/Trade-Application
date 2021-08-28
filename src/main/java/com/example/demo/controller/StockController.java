package com.example.demo.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.History;
import com.example.demo.entities.Holding;
import com.example.demo.entities.Stock;
import com.example.demo.service.StockService;

@RestController
@RequestMapping("api/stock")

public class StockController {
	
	@Autowired
	StockService service;
	
	@GetMapping(value = "/")
	public List<Stock> getAllStock() {
		return service.getAllstocks();
	}
	
	@GetMapping(value = "/get/{id}")
	public Stock getStockById(@PathVariable("id") int id) {
	  return service.getStock(id);
	}

	@PostMapping(value = "/")
	public Stock addStock(@RequestBody Stock stock) {
		return service.newStock(stock);
	}

	@PutMapping(value = "/")
	public Stock editStock(@RequestBody Stock stock) {
		return service.saveStock(stock);
	}

	@DeleteMapping(value = "/{id}")
	public int deleteStock(@PathVariable ("id") int id) {
		return service.deleteStock(id);
	}
	
	 @GetMapping(value = "/buy/{stockTicker}/{volume}") 
	 public String BuyStock(@PathVariable ("stockTicker") String stockTicker, @PathVariable ("volume") int volume) { 
		 return service.BuyStock(stockTicker, volume); 
	}
	 
	 @GetMapping(value = "/sell/{stockTicker}/{volume}") 
	 public String SellStock(@PathVariable ("stockTicker") String stockTicker, @PathVariable ("volume") int volume) { 
		 return service.SellStock(stockTicker, volume); 
	}
	 
	 @GetMapping(value = "/holdings") 
	 public List<Holding>  Holdings() { 
		 return service.holdings(); 
	}
	 
	 
	 @GetMapping(value = "/history") 
	 public List<History>  history() { 
		 return service.history(); 
	}
	 
	@GetMapping(value = "/dropDown1")
	public List<Map<String, Object>> dropDown1() {
		return service.dropDown1();
	}
	
	@GetMapping(value = "/getTicker/{id}")
	public String getTicker(@PathVariable ("id") int id) {
		return service.getTicker(id);
	}
}


