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
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ContactServlet extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
	                  throws IOException,ServletException
		{
			resp.setContentType("text/plain");
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			
			if(req.getParameter("contactId") == null)
			{
				resp.getWriter().println("get all contacts!!!");
				List contacts = new ArrayList();
				
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
					rs = stmt.executeQuery("select * from contact");
					while(rs.next())
					{
						Map contact = new HashMap();
						contact.put("id", rs.getLong("id"));
						contact.put("name", rs.getString("name"));
						contact.put("mobile", rs.getString("mobile"));
						contact.put("vpmn", rs.getString("vpmn"));
						contact.put("job", rs.getString("job"));
						
						contacts.add(contact);
						resp.getWriter().println("id: " + contact.get("id"));
						resp.getWriter().println("name: " + contact.get("name"));
						resp.getWriter().println("mobile: " + contact.get("mobile"));
						resp.getWriter().println("vpmn: " + contact.get("vpmn"));						
						resp.getWriter().println("job: " + contact.get("job"));
					}
					
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
			else
			{
				resp.getWriter().println("get contact by id: " + req.getParameter("contactId"));
				Long id = null;
				String name = null;
				String mobile = null;
				String vpmn = null;
				String email = null;
				String homeAddress = null;
				String officeAddress = null;
				String memo = null;
				String job = null;
				int jobLevel = 0;

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
					if(rs.next())
					{
						id = rs.getLong("id");
						name = rs.getString("name");
						mobile = rs.getString("mobile");
						vpmn = rs.getString("vpmn");
						email = rs.getString("email");
						homeAddress = rs.getString("home_address");
						officeAddress = rs.getString("office_address");
						memo = rs.getString("memo");
						job = rs.getString("job");
						jobLevel = rs.getInt("job_level");
					}
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
				if(id != null)
				{
					resp.getWriter().println("id: " + id);
					resp.getWriter().println("name: " + name);
					resp.getWriter().println("mobile: " + mobile);
					resp.getWriter().println("vpmn: " + vpmn);
					resp.getWriter().println("email: " + email);
					resp.getWriter().println("homeAddress: " + homeAddress);
					resp.getWriter().println("officeAddress: " + officeAddress);
					resp.getWriter().println("memo: " + memo);
					resp.getWriter().println("job: " + job);
					resp.getWriter().println("jobLevel: " + jobLevel);
				}
				else
				{
					resp.getWriter().println("contact not found!!!.");
				}
			}
		}
		
}