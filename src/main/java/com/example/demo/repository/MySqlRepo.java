package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Stock;

@Repository
public class MySqlRepo implements StockRepository{

	@Autowired 
	JdbcTemplate template;
	
	@Override
	public List<Stock> getAllStocks() {
		// TODO Auto-generated method stub
		
		String sql ="SELECT ID,STOCKTICKER, PRICE, VOLUME,BuyOrSell,StatusCode FROM Stocks";
		return template.query(sql,new StockRowMapper());
	
		}

	@Override
	public Stock getStockById(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM Stocks WHERE ID=?";
		return template.queryForObject(sql, new StockRowMapper(), id);

	}

	@Override
	public Stock updateStock(Stock stock) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Stocks SET stockTicker = ?, price = ? , volume = ?, buyOrSell = ?, statusCode = ? " +
				"WHERE ID = ?";

		template.update(sql,stock.getStockTicker(),stock.getPrice(),stock.getVolume(),stock.getBuyOrSell(),stock.getStatusCode(),stock.getId());
		return stock;
	}


	@Override
	public int deleteStock(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Stocks WHERE ID = ?";
		template.update(sql,id);
		return id;
	}


	@Override
	public Stock addStock(Stock stock) {
		
			// TODO Auto-generated method stub
			String sql = "INSERT INTO Stocks(StockTicker, Price, Volume, BuyOrSell, StatusCode)" +
					"VALUES(?,?,?,?,?)";
			template.update(sql,stock.getStockTicker(),stock.getPrice(),stock.getVolume(),stock.getBuyOrSell(),stock.getStatusCode());
			return stock;
		}

	
	class StockRowMapper implements RowMapper<Stock>
	{
		public Stock mapRow (ResultSet rs, int rowNum) throws SQLException
		{
			return new Stock(rs.getInt("ID"),
					rs.getString("StockTicker"),
					rs.getDouble("Price"),
					rs.getInt("volume"),
					rs.getString("BuyOrSell"),
					rs.getInt("StatusCode"));
		}
	}

}
