package com.baldurtech;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

public class ContactCreateServlet extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException
	{
		getServletContext().getRequestDispatcher("/WEB-INF/contact/create.jsp").forward(req, resp);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException
	{
		resp.getWriter().println(req.getParameter("name"));
		resp.getWriter().println(req.getParameter("mobile"));
		resp.getWriter().println(req.getParameter("vpmn"));
		resp.getWriter().println(req.getParameter("email"));
		resp.getWriter().println(req.getParameter("homeAddress"));
		resp.getWriter().println(req.getParameter("officeAddress"));
		resp.getWriter().println(req.getParameter("job"));
		resp.getWriter().println(req.getParameter("jobLevel"));
		resp.getWriter().println("hang zai has saved!!!");
	}
}