package com.example.demo.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.History;
import com.example.demo.entities.Holding;
import com.example.demo.entities.Stock;
import com.example.demo.entities.userdata;
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
	
	@GetMapping(value="/login/{username}/{pwd}")
	public String login(@PathVariable ("username") String username, @PathVariable ("pwd") String pwd)
	{
		System.out.println("controller called" + username + pwd);

		return service.login(username,pwd);
		
	}
	
	@PostMapping(value="/login2/{username}/{pwd}")
	public String login2(@RequestBody userdata u)
	{
		System.out.println("controller called" + u.getName() + u.getPassword());

		return service.login2(u);
		
	}
	
	@PostMapping(value="/register/{username}/{pwd}/{email}")
	public String register(@PathVariable ("username") String username, @PathVariable ("pwd") String pwd,@PathVariable ("email") String email)
	{
		return service.register(username,pwd,email);
		
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
	
	
	 @RequestMapping(value = "/buy/{userName}/{stockTicker}/{volume}", method = RequestMethod.POST) 
	 public String BuyStock(@PathVariable ("userName") String userName, @PathVariable ("stockTicker") String stockTicker, @PathVariable ("volume") int volume) throws MissingPathVariableException { 
			System.out.println("controller called");

		 String result=  service.BuyStock(userName, stockTicker, volume);
		 System.out.println(result);
		 return result; 
	}
	 
	 @GetMapping(value = "/sell/{stockTicker}/{volume}/{userName}") 
	 public String SellStock(@PathVariable ("stockTicker") String stockTicker, @PathVariable ("volume") int volume,
			 @PathVariable ("userName") String userName) { 
		 return service.SellStock(stockTicker, volume, userName); 
	}
	 
	 @GetMapping(value = "/holdings/{userName}") 
	 public List<Holding>  Holdings(@PathVariable ("userName") String userName) { 
		 return service.holdings(userName); 
	}
	 
	 
	 @GetMapping(value = "/history/{userName}") 
	 public List<History>  history(@PathVariable ("userName") String userName) { 
		 return service.history(userName); 
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


