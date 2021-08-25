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
	
	
		/*
		 * @Override public List<Stock> StockPresent(String stockTicker) { // TODO
		 * Auto-generated method stub
		 * 
		 * //String sql = " SELECT COUNT(1) FROM Stocks s WHERE s.stockTicker = ?";
		 * 
		 * return template.query("SELECT COUNT(id) FROM Stocks WHERE stockTicker = ?",
		 * new StockRowMapper(),stockTicker);
		 * 
		 * }
		 */
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


	@Override
	public String buyStock(String stockTicker, int volume) {

		String sql1= "SELECT COUNT(ID) FROM STOCKS WHERE STOCKTICKER=?";
		int stockPresent =template.queryForObject(sql1, Integer.class, stockTicker);

		if(stockPresent==0)
		{
			String result= "Stock not present";
			return result;
		}
		
		String sql2= "SELECT VOLUME FROM STOCKS WHERE STOCKTICKER=?";
		int dbVolume=template.queryForObject(sql2, Integer.class, stockTicker);
		
		if(stockPresent==1 && dbVolume>=volume)
		{
			int modifiedVolume=dbVolume-volume;
			
			String sql3= "UPDATE Stocks SET VOLUME = ? WHERE STOCKTICKER=?"; 
			template.update(sql3,modifiedVolume,stockTicker);
			

			String sql4= "SELECT COUNT(ID) FROM HOLDINGS WHERE STOCKTICKER=?";
			int stockInHistory =template.queryForObject(sql4, Integer.class, stockTicker);

			if(stockInHistory==1)
			{
				String sql5= "SELECT VOLUME FROM HOLDINGS WHERE STOCKTICKER=?";
				int dbVol=template.queryForObject(sql5, Integer.class, stockTicker);
				int UpdateVol=volume+dbVol;

				String sql6= "UPDATE HOLDINGS SET VOLUME = ? WHERE STOCKTICKER=?"; 
				template.update(sql6,UpdateVol,stockTicker);
		
			}
			
			else
			{
				String sql5 = "INSERT INTO Holdings(StockTicker, Volume) VALUES(?,?)";
				template.update(sql5, stockTicker, volume);
			}

			String sql6 = "INSERT INTO History (StockTicker, Volume, BuyOrSell) VALUES(?,?,?)";
			template.update(sql6, stockTicker, volume, "Buy");
		
			String result="Stocks Buied Successfully";
			return result;
		}
		
		else
		{
				String result= "insufficient volume";
				return result;
	    }

		
	}

}
