package com.example.demo.entities;

public class History {

	private int id;
	private String stockTicker;
	private int price;
	private int volume;
	private String DateTime;
	private String BuyOrSell;
	

	public History()
	{	
	}
	
	public History(int id, String dateTime, String stockTicker, int price, int volume, String buyOrSell) {
		super();
		this.id = id;
		this.stockTicker = stockTicker;
		this.price = price;
		this.volume = volume;
		DateTime = dateTime;
		BuyOrSell = buyOrSell;
	}
	
	public String getDateTime() {
		return DateTime;
	}

	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}

	public String getBuyOrSell() {
		return BuyOrSell;
	}

	public void setBuyOrSell(String buyOrSell) {
		BuyOrSell = buyOrSell;
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
