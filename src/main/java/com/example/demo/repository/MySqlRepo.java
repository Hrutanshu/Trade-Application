package com.example.demo.repository;

import java.sql.ResultSet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.History;
import com.example.demo.entities.Holding;
import com.example.demo.entities.Stock;

@Repository
public class MySqlRepo implements StockRepository{

	private static final int String = 0;
	@Autowired 
	JdbcTemplate template;
	
	@Override
	public List<Stock> getAllStocks() {		
		String sql ="SELECT ID, COMPANY,STOCKTICKER, PRICE, VOLUME FROM Stocks";
		return template.query(sql,new StockRowMapper());
	
		}
	
	@Override
	public List<Map<String, Object>> dropDown1() {
		String sql ="SELECT id, stockTicker FROM Stocks order by stockTicker";
		return template.queryForList(sql);
		}
	
	@Override
	public String getTicker(int id)
	{
		String sql="SELECT StockTicker FROM Stocks WHERE ID=?";
		return template.queryForObject(sql, String.class ,id);
	}

	@Override
	public Stock getStockById(int id) {
		String sql="SELECT * FROM Stocks WHERE ID=?";
		return template.queryForObject(sql, new StockRowMapper(), id);

	}

	@Override
	public Stock updateStock(Stock stock) {
		String sql = "UPDATE Stocks SET stockTicker = ?, company= ?, price = ? , volume = ? WHERE ID = ?";
		template.update(sql,stock.getStockTicker(),stock.getCompany(), stock.getPrice(),stock.getVolume(),stock.getId());
		return stock;
	}


	@Override
	public int deleteStock(int id) {
		String sql = "DELETE FROM Stocks WHERE ID = ?";
		template.update(sql,id);
		return id;
	}


	@Override
	public Stock addStock(Stock stock) {
		
			String sql = "INSERT INTO Stocks(Company, StockTicker, Price, Volume)" +
					"VALUES(?,?,?,?)";
			template.update(sql,stock.getCompany(), stock.getStockTicker(),stock.getPrice(),stock.getVolume());
			return stock;
		}


	@Override
	public String buyStock( String userName, String stockTicker, int volume) {

		String sql1= "SELECT COUNT(ID) FROM STOCKS WHERE STOCKTICKER=?";
		int stockPresent = template.queryForObject(sql1, Integer.class, stockTicker);

		if(stockPresent==0)
			return "Stock not present";
		
		System.out.println("buy called");
		String sql2= "SELECT VOLUME FROM STOCKS WHERE STOCKTICKER=?";
		int dbVolume=template.queryForObject(sql2, Integer.class, stockTicker);
		
		if(stockPresent==1 && dbVolume>=volume)
		{
			System.out.println("stock there called");

			String sql= "SELECT PRICE FROM Stocks WHERE STOCKTICKER=?";
			int price=template.queryForObject(sql, Integer.class, stockTicker);
			
			Date d1 = new Date();
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String date= sdf.format(d1);
			
			System.out.println("inerted into history");

			String sql7 = "INSERT INTO History (userName, DateTime,StockTicker, Price, Volume, BuyOrSell) VALUES(?,?,?,?,?,?)";
			template.update(sql7, userName, date, stockTicker, price, volume, "Bought");
			
			int modifiedVolume=dbVolume-volume;
			

			String sql9 = "select  count(*) from history ";
			int count = template.queryForObject(sql9, Integer.class);
			System.out.println("history called called");

			wait(15000);

			
			String sql8 = "select status_code from history where id=?";
			int status = template.queryForObject(sql8, Integer.class,count );
			
			
			/*
			String sql9 = "select count(*) from history";
			int count = template.queryForObject(sql9, Integer.class);

			wait(15000);
			String sql8 = "select status_code from history where id=?";
			int status = template.queryForObject(sql8, Integer.class, count);
			*/
			
			System.out.println(status);
			if(status == 2) {
				String sql3= "UPDATE Stocks SET VOLUME = ? WHERE STOCKTICKER=?"; 
				template.update(sql3,modifiedVolume,stockTicker);
				
				String sql4= "SELECT COUNT(ID) FROM HOLDINGS WHERE (STOCKTICKER=? AND USERNAME= ?)";
				int stockInHistory =template.queryForObject(sql4, Integer.class, stockTicker, userName);
				
				if(stockInHistory==1)
				{
					String sql5= "SELECT VOLUME FROM HOLDINGS WHERE (STOCKTICKER=? AND USERNAME=?";
					int dbVol=template.queryForObject(sql5, Integer.class, stockTicker, userName);
					int UpdateVol=volume+dbVol;
	
					String sql6= "UPDATE HOLDINGS SET VOLUME = ? WHERE (STOCKTICKER=? AND USERNAME=?)"; 
					template.update(sql6,UpdateVol,stockTicker, userName);
			
				}
				
				else
				{
					String sql5 = "INSERT INTO Holdings(userName, StockTicker, Price, Volume) VALUES(?,?,?,?)";
					template.update(sql5, userName, stockTicker, price, volume);
				}
				System.out.println("succeess");
				return "Stocks Bought Successfully";
			}
			else if(status == 3) {
				System.out.println("fail");

				return "Transaction failed";
			}
			else {
				System.out.println("started");

				return "Order initiated";
			}

		}
		
		else
			return "Insufficient Volume";
	}

	@Override
	public String sellStock(String stockTicker, int volume,  String userName) {

		String sql1= "SELECT COUNT(ID) FROM HOLDINGS WHERE ( STOCKTICKER=? AND USERNAME=?)";
		int stockPresent =template.queryForObject(sql1, Integer.class, stockTicker, userName);

		if(stockPresent==0)
			return "Stock not in holdings";
		
		String sql2= "SELECT VOLUME FROM HOLDINGS WHERE (STOCKTICKER=? AND USERNAME=?) ";
		int dbVolume=template.queryForObject(sql2, Integer.class, stockTicker,userName);
		
		String sql3= "SELECT VOLUME FROM stocks WHERE STOCKTICKER=?";
		int dataBVolume=template.queryForObject(sql3, Integer.class, stockTicker);
		
		if(stockPresent==1 && dbVolume>=volume)
		{
			Date d1 = new Date();
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String date= sdf.format(d1);

			String sql6= "SELECT price FROM stocks WHERE STOCKTICKER=?";
			int price=template.queryForObject(sql6, Integer.class, stockTicker);
			
			String sql7 = "INSERT INTO History (userName, DateTime,StockTicker, Price, Volume, BuyOrSell) VALUES(?,?,?,?,?,?)";
			template.update(sql7, userName, date, stockTicker, price, volume, "Sold");
			
			int HoldingVol=dbVolume-volume;
			int modifiedVolume=dataBVolume+volume;
			

			String sql9 = "select  count(*) from history ";
			int count = template.queryForObject(sql9, Integer.class);
			
			wait(25000);
			
			String sql8 = "select status_code from history where id=?";
			int status = template.queryForObject(sql8, Integer.class,count );
			
			if(status == 2) {
				if(HoldingVol==0)
				{
					String sql4= "DELETE FROM Holdings WHERE (STOCKTICKER=? AND USERNAME=?)"; 
					template.update(sql4,stockTicker, userName);	
				}
				else
				{
					
					String sql4= "UPDATE Holdings SET VOLUME = ? WHERE (STOCKTICKER=? AND USERNAME=?)"; 
					template.update(sql4,HoldingVol,stockTicker, userName);
				}	
				String sql5= "UPDATE stocks SET VOLUME = ? WHERE STOCKTICKER=?"; 
				template.update(sql5,modifiedVolume,stockTicker);
				return "Stocks Sold Successfully";
			}
			else if(status == 3) {
				return "Transaction failed!";
			}
			else {
				return "Transaction processing";
			}
			
		}
		
		else
			return "Insufficient Volume";
	}

	@Override
	public List<Holding> holdings(String userName) {
		String sql ="SELECT ID,STOCKTICKER,VOLUME,PRICE FROM Holdings WHERE USERNAME=?";
		return template.query(sql,new HoldingRowMapper(),userName);
		}
	
	@Override
	public List<History> history(String userName) {
		String sql ="SELECT ID,DATETIME, STOCKTICKER, PRICE, VOLUME, BUYORSELL, STATUS_CODE FROM HISTORY  WHERE USERNAME=? order by DateTime DESC";
		return template.query(sql,new HistoryRowMapper(), userName);
		}
	
	class HoldingRowMapper implements RowMapper<Holding>
	{
		public Holding mapRow (ResultSet rs, int rowNum) throws SQLException
		{
			return new Holding(rs.getInt("ID"),
					rs.getString("StockTicker"),
					rs.getInt("Price"),
					rs.getInt("volume"));
		}
	}
	
	class HistoryRowMapper implements RowMapper<History>
	{
		public History mapRow (ResultSet rs, int rowNum) throws SQLException
		{
			return new History(rs.getInt("ID"),
					rs.getString("DateTime"),
					rs.getString("StockTicker"),
					rs.getInt("Price"),
					rs.getInt("volume"),
					rs.getString("BuyOrSell"),
					rs.getInt("status_code"));
		}
	}
	

	class StockRowMapper implements RowMapper<Stock>
	{
		public Stock mapRow (ResultSet rs, int rowNum) throws SQLException
		{
			return new Stock(rs.getInt("ID"),
					rs.getString("Company"),
					rs.getString("StockTicker"),
					rs.getInt("Price"),
					rs.getInt("volume"));
		}
	}
	
	public static void wait(int ms)
	{
	    try
	    {
	        Thread.sleep(ms);
	    }
	    catch(InterruptedException ex)
	    {
	        Thread.currentThread().interrupt();
	    }
	}

	@Override
	public String login(java.lang.String username, java.lang.String pwd) {
		// TODO Auto-generated method stub
		String sql= "SELECT USERPASSWORD FROM USERDATA WHERE USERNAME=?";
		String password=template.queryForObject(sql, String.class, username);
		if(password.equals(pwd))
			return "1";
		else return "0";
		
	}

	@Override
	public String register(java.lang.String username, java.lang.String pwd, java.lang.String email) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO USERDATA(EMAIL, USERPASSWORD, USERNAME) VALUES (?,?,?)";
		int response=template.update(sql, email, pwd, username);
		System.out.println("responseeeeeeeeeeee"+ response);
		if(response==0)
			return "0";
		return "1";
	}

}
