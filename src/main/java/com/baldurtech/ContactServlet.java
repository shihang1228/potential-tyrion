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
		ContactService contactService = new ContactService();
		
		resp.setContentType("text/plain");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
			
		if(req.getParameter("contactId") == null)
		{
			resp.getWriter().println("get all contacts!!!");
			for(Contact contact: contactService.getAllContacts())
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
			Contact contact = contactService.getContactById(Long.valueOf(req.getParameter("contactId")));
				
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
		
}
