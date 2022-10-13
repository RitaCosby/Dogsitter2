package com.dogsitter.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String MDY_DATE_FORMAT = "MM-dd-yyyy";	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(true);
		Date d = new Date();
		String strDate = getMdyDateString(d);
		session.setAttribute("currdate", strDate);
       
		
		
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
		rd.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = null;
		if (request.getParameter("register") != null) {
		   rd = getServletContext().getRequestDispatcher("/registerDog.jsp");

		}
		if (request.getParameter("search") != null) {
			 rd = getServletContext().getRequestDispatcher("/search.jsp");

		}
		if (request.getParameter("scheduleVisit") != null) {
		     rd = getServletContext().getRequestDispatcher("/scheduleVisit.jsp");

		}
		HttpSession session = request.getSession(true);
		session.removeAttribute("owner");
		session.removeAttribute("mode");
		rd.forward(request, response);

	}
	private String getMdyDateString(Date date){
	    SimpleDateFormat sdf = new SimpleDateFormat(MDY_DATE_FORMAT);
	    String strDate = sdf.format(date);	
	    return strDate;
	}

}
