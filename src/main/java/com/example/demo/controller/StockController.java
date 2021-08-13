package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping(value = "/{id}")
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
	public int deleteStock(@PathVariable int id) {
		return service.deleteStock(id);
	}

}


