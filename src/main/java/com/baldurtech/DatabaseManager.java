package com.baldurtech;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class DatabaseManager
{
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	public DatabaseManager execute(String sql)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch(Exception ex)
		{
				
		}
		
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=&useUnicode=true&characterEncoding=UTF-8");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		return this;
	}
	
	public void close()
	{
		if(rs != null)
		{
			try
			{
				rs.close();
			}
			catch(SQLException sqle)
			{
			
			}
		}
		if(stmt != null)
		{
			try
			{
				stmt.close();
			}
			catch(SQLException sqle)
			{
				
			}
		}
		if(conn != null)
		{
			try
			{
				conn.close();
			}
			catch(SQLException sqle)
			{
				
			}
		}
	}
	
}