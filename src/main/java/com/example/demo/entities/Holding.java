package com.example.demo.entities;

public class Holding {

	private int id;
	private String stockTicker;
	private int price;
	private int volume;
	
	public Holding()
	{
		
	}
	
	public Holding(int id, String stockTicker, int price, int volume) {
		super();
		this.id = id;
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
	public void setPrice(int price) {
		this.price = price;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
}
