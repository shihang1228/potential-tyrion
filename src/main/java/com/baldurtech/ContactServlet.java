package com.baldurtech;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ContactServlet extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
	                  throws IOException,ServletException
		{
			resp.setContentType("text/html");
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			}
			catch(Exception ex)
			{
				
			}
			
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=&useUnicode=true&characterEncoding=utf-8");
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select * from contact where id=" + req.getParameter("contactId"));
				rs.next();
				resp.getWriter().println(rs.getString("name"));
			}
			catch(SQLException sqle)
			{
				resp.getWriter().println("Cannot connect to DB");
				resp.getWriter().println(sqle.getMessage());
				sqle.printStackTrace();
			}
			
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