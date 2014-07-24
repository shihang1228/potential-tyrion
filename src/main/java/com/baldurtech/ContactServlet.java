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
				for(Contact contact: getAllContacts())
				{
					resp.getWriter().println("id: " + contact.getId());
					resp.getWriter().println("name: " + contact.getName());
					resp.getWriter().println("mobile: " + contact.getMobile());
					resp.getWriter().println("vpmn: " + contact.getVpmn());						
					resp.getWriter().println("job: " + contact.getJob());
				}
			}
			else
			{
				resp.getWriter().println("get contact by id: " + req.getParameter("contactId"));
				Map contact = getContactById(Long.valueOf(req.getParameter("contactId")));
				
				if(contact.get("id") != null)
				{
					resp.getWriter().println("id: " + contact.get("id"));
					resp.getWriter().println("name: " + contact.get("name"));
					resp.getWriter().println("mobile: " + contact.get("mobile"));
					resp.getWriter().println("vpmn: " + contact.get("vpmn"));
					resp.getWriter().println("email: " + contact.get("email"));
					resp.getWriter().println("homeAddress: " + contact.get("homeAddress"));
					resp.getWriter().println("officeAddress: " + contact.get("officeAddress"));
					resp.getWriter().println("memo: " + contact.get("memo"));
					resp.getWriter().println("job: " + contact.get("job"));
					resp.getWriter().println("jobLevel: " + contact.get("jobLevel"));
				}
				else
				{
					resp.getWriter().println("contact not found!!!.");
				}
			}
		}
		private List<Contact> getAllContacts()
		{
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
				conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=&useUnicode=true&characterEncoding=UTF-8");
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select * from contact");
				while(rs.next())
				{
					Contact contact = new Contact();
					
					contact.setId(rs.getLong("id"));
					contact.setName(rs.getString("name"));
					contact.setMobile(rs.getString("mobile"));
					contact.setVpmn(rs.getString("vpmn"));
					contact.setJob(rs.getString("job"));
					
					contacts.add(contact);
				}
			}
			catch(SQLException sqle)
			{
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
			return contacts;
		}
		
		private Map getContactById(Long id)
		{
			Map contact = new HashMap();
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
				rs = stmt.executeQuery("select * from contact where id=" + id);
				if(rs.next())
				{
					contact.put("id", rs.getLong("id"));
					contact.put("name", rs.getString("name"));
					contact.put("mobile", rs.getString("mobile"));
					contact.put("vpmn", rs.getString("vpmn"));
					contact.put("email", rs.getString("email"));
					contact.put("homeAddress", rs.getString("home_address"));
					contact.put("officeAddress", rs.getString("office_address"));
					contact.put("memo", rs.getString("memo"));
					contact.put("job", rs.getString("job"));
					contact.put("jobLevel", rs.getInt("job_level"));
				}
			}
			catch(SQLException sqle)
			{
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
			return contact;
		}
		
}