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
				Contact contact = getContactById(Long.valueOf(req.getParameter("contactId")));
				
				if(contact.getId() != null)
				{
					resp.getWriter().println("id: " + contact.getId());
					resp.getWriter().println("name: " + contact.getName());
					resp.getWriter().println("mobile: " + contact.getMobile());
					resp.getWriter().println("vpmn: " + contact.getVpmn());
					resp.getWriter().println("email: " + contact.getEmail());
					resp.getWriter().println("homeAddress: " + contact.getHomeAddress());
					resp.getWriter().println("officeAddress: " + contact.getOfficeAddress());
					resp.getWriter().println("memo: " + contact.getMemo());
					resp.getWriter().println("job: " + contact.getJob());
					resp.getWriter().println("jobLevel: " + contact.getJobLevel());
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
					Contact contact = getContactFromResultSet(rs);
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
		
		private Contact getContactById(Long id)
		{
			Contact contact = new Contact();
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
					contact = getContactFromResultSet(rs);
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
		
		public Contact getContactFromResultSet(ResultSet rs) throws SQLException
		{
			Contact contact = new Contact();
			
			contact.setId(rs.getLong("id"));
			contact.setName(rs.getString("name"));
			contact.setMobile(rs.getString("mobile"));
			contact.setVpmn(rs.getString("vpmn"));
			contact.setEmail(rs.getString("email"));
			contact.setHomeAddress(rs.getString("home_address"));
			contact.setOfficeAddress(rs.getString("office_address"));
			contact.setMemo(rs.getString("memo"));
			contact.setJob(rs.getString("job"));
			contact.setJobLevel(rs.getInt("job_level"));
			
			return contact;
		}
		
}