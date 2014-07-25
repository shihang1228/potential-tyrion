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
				
				if(contact != null)
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
			return findAllContactsBySql("select * from contact");
		}
		public List<Contact> findAllContactsBySql(String sql)
		{
			List<Contact> contacts = new ArrayList<Contact>();
			DatabaseManager db = createDatabaseConnectionAndExecute(sql);
			try
			{
				while(db.rs.next())
				{
					contacts.add(createContactFromResultSet(db.rs));
				}
			}
			catch(SQLException sqle)
			{
				sqle.printStackTrace();
			}
			
			closeDatabaseManager(db);
			return contacts;
		}
		
		private Contact getContactById(Long id)
		{	
			return findFirstContactBySql("select * from contact where id = " + id);
		}
		public Contact findFirstContactBySql(String sql)
		{
			List<Contact> contacts = findAllContactsBySql(sql);	
			if(contacts.size() > 0)
			{
				return contacts.get(0);
			}
			else
			{
				return null;
			}
		}
		
		public Contact createContactFromResultSet(ResultSet rs) throws SQLException
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
		
	public DatabaseManager createDatabaseConnectionAndExecute(String sql)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch(Exception ex)
		{
				
		}
			
		DatabaseManager db = new DatabaseManager();
			
		try
		{
			db.conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=&useUnicode=true&characterEncoding=UTF-8");
			db.stmt = db.conn.createStatement();
			db.rs = db.stmt.executeQuery(sql);
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		return db;
	}
	public void closeDatabaseManager(DatabaseManager db)
	{
		if(db.rs != null)
		{
			try
			{
				db.rs.close();
			}
			catch(SQLException sqle)
			{
			
			}
		}
		if(db.stmt != null)
		{
			try
			{
				db.stmt.close();
			}
			catch(SQLException sqle)
			{
				
			}
		}
		if(db.conn != null)
		{
			try
			{
				db.conn.close();
			}
			catch(SQLException sqle)
			{
				
			}
		}
	}
		
}

class DatabaseManager
{
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
}