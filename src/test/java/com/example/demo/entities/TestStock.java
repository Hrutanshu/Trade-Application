package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestStock {

	@Test
	public void testsetId() throws NoSuchFieldException, IllegalAccessException {
        final Stock obj = new Stock();

        obj.setId(5);

        final java.lang.reflect.Field field = obj.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals(Integer.valueOf(5),field.get(obj));
    
    }
    
	@Test
    public void testsetStockTicker() throws NoSuchFieldException, IllegalAccessException {
        final Stock obj = new Stock();

        obj.setStockTicker("AMZ");

        final java.lang.reflect.Field field = obj.getClass().getDeclaredField("stockTicker");
        field.setAccessible(true);
        assertEquals("AMZ",field.get(obj));
    }
    
	@Test
    public void testsetPrice() throws NoSuchFieldException, IllegalAccessException {
        final Stock obj = new Stock();

        obj.setPrice(123);

        final java.lang.reflect.Field field = obj.getClass().getDeclaredField("price");
        field.setAccessible(true);
        assertEquals(Integer.valueOf(123),field.get(obj));
    
    }

	@Test
	public void testsetVolume() throws NoSuchFieldException, IllegalAccessException {
        final Stock obj = new Stock();

        obj.setVolume(521);

        final java.lang.reflect.Field field = obj.getClass().getDeclaredField("volume");
        field.setAccessible(true);
        assertEquals(Integer.valueOf(521),field.get(obj));
    
    }
    
	@Test
    public void testsetCompany() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Stock obj = new Stock();

        //when
        obj.setCompany("AMAZON");

        //then
        final java.lang.reflect.Field field = obj.getClass().getDeclaredField("company");
        field.setAccessible(true);
        assertEquals("AMAZON",field.get(obj));
    }

}
