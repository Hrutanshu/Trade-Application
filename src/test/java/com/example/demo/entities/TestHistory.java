package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestHistory {

		
		@Test
		public void testsetId() throws NoSuchFieldException, IllegalAccessException {
	        final History obj = new History();

	        obj.setId(5);

	        final java.lang.reflect.Field field = obj.getClass().getDeclaredField("id");
	        field.setAccessible(true);
	        assertEquals(Integer.valueOf(5),field.get(obj));
	    
	    }
	    
		@Test
	    public void testSetBuyOrSell() throws NoSuchFieldException, IllegalAccessException {
	        final History obj = new History();

	        obj.setBuyOrSell("BUY");

	        final java.lang.reflect.Field field = obj.getClass().getDeclaredField("BuyOrSell");
	        field.setAccessible(true);
	        assertEquals("BUY",field.get(obj));
	    }
		
		@Test
	    public void testDateTime() throws NoSuchFieldException, IllegalAccessException {
	        final History obj = new History();

	        obj.setDateTime("27/08/2021 12:12:12");

	        final java.lang.reflect.Field field = obj.getClass().getDeclaredField("DateTime");
	        field.setAccessible(true);
	        assertEquals("27/08/2021 12:12:12",field.get(obj));
	    }
		
		@Test
	    public void testsetStockTicker() throws NoSuchFieldException, IllegalAccessException {
	        final History obj = new History();

	        obj.setStockTicker("AMZ");

	        final java.lang.reflect.Field field = obj.getClass().getDeclaredField("stockTicker");
	        field.setAccessible(true);
	        assertEquals("AMZ",field.get(obj));
	    }
	    
		@Test
	    public void testsetPrice() throws NoSuchFieldException, IllegalAccessException {
	        final History obj = new History();

	        obj.setPrice(123);

	        final java.lang.reflect.Field field = obj.getClass().getDeclaredField("price");
	        field.setAccessible(true);
	        assertEquals(Integer.valueOf(123),field.get(obj));
	    
	    }

		@Test
		public void testsetVolume() throws NoSuchFieldException, IllegalAccessException {
	        final History obj = new History();

	        obj.setVolume(521);

	        final java.lang.reflect.Field field = obj.getClass().getDeclaredField("volume");
	        field.setAccessible(true);
	        assertEquals(Integer.valueOf(521),field.get(obj));
	    
	    }
	    


	}


