package com.example.demo.entities;

public class Stock {

	private int id;
	private String company;
	private String stockTicker;
	private double price;
	private int volume;
	
	public Stock()
	{
		
	}
	
	public Stock(int id, String company, String stockTicker, double price, int volume) {
		super();
		this.id = id;
		this.company = company;
		this.stockTicker = stockTicker;
		this.price = price;
		this.volume = volume;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStockTicker() {
		return stockTicker;
	}
	public void setStockTicker(String stockTicker) {
		this.stockTicker = stockTicker;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}

	
}
