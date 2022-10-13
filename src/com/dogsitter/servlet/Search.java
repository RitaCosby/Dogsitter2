package com.dogsitter.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dogsitter.dal.DataStore;
import com.dogsitter.model.Owner;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		session.removeAttribute("ownerList");
		String ownerName = request.getParameter("name");
		ArrayList<Owner> owners = null;
		boolean found = false;
		if(!(ownerName == null)&& ownerName != "") {
		   String[] x = new String[2];
		   x = ownerName.split(" ");
		   String fn = null;
		   String ln = null;
		   if(x.length > 0) {
			    fn = x[0];
		   }
		   if(x.length > 1) {
				   ln = x[1];
		   }
		   DataStore datastore = new DataStore();
		   
			// do we have first name and last name? only 1 name field
			try {
				owners = datastore.findOwnerByName(fn, ln);
				if(owners.size()>0) {
					found=true;
				}
			}
			catch (Exception e) {
				throw new IOException("databasae error");
			}
		}
		if (!(found)) {
			String email = request.getParameter("email");
			if (email != null) {

				DataStore datastore = new DataStore();
				// do we have first name and last name? only 1 name field

				try {
					owners = datastore.findOwnerByEmail(email);
					if (owners.size() > 0) {
						found = true;
					}
				} catch (Exception e) {
					throw new IOException("databasae error");
				}
			}
		}
		if (!(found)) {
			String strPhone = request.getParameter("phone");
			if (strPhone != null) {
				int phone = Integer.parseInt(strPhone);

				DataStore datastore = new DataStore();
				// do we have first name and last name? only 1 name field

				try {
					owners = datastore.findOwnerByPhone(phone);
					if (owners.size() > 0) {
						found = true;
					}
				} catch (Exception e) {
					throw new IOException("databasae error");
				}
			}
		}
		if (!(found)) {
			String name = request.getParameter("dog_name");
			if (name != null) {
				DataStore datastore = new DataStore();
				// do we have first name and last name? only 1 name field

				try {
					owners = datastore.findOwnerByDogN(name);
					if (owners.size() > 0) {
						found = true;
					}
				} catch (Exception e) {
					throw new IOException("databasae error");
				}
			}
		}

		session.setAttribute("ownerList", owners);
		session.setAttribute("mode", "ADD");	
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/search.jsp");
		rd.forward(request, response);
	}

}
