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
			resp.getWriter().println("get contact by id:" + req.getParameter("contactId"));
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			}
			catch(Exception ex)
			{
				
			}
			
			try
			{
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
				resp.getWriter().println("connect sussess!!!");
			}
			catch(SQLException sqle)
			{
				resp.getWriter().println("Cannot connect to DB");
				resp.getWriter().println(sqle.getMessage());
				sqle.printStackTrace();
			}
			
		}
		
}