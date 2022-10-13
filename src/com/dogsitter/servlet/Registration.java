package com.dogsitter.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dogsitter.dal.DataStore;
import com.dogsitter.model.Address;
import com.dogsitter.model.Dog;
import com.dogsitter.model.Info;
import com.dogsitter.model.Owner;


/**
 * Servlet implementation class RegistrationD
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: /registration/").append(request.getContextPath());
		//check details/edit button?
		
		
		Owner o = null;
		String strOwnerId = request.getParameter("ownerId");
		String strDogId = request.getParameter("dogId");
	    int ownerId = Integer.parseInt(strOwnerId);
	    int dogId = Integer.parseInt(strDogId);

		DataStore datastore = new DataStore();
		try {
			o = datastore.editDetails(ownerId, dogId);
			
		}
		catch (Exception e) {
			throw new IOException("databasae error");
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("owner", o);
		session.setAttribute("dog", o.getDog());
		session.setAttribute("mode", "UPDATE");		
		/*
		 * Address address = new Address(); Dog dog = new Dog();
		 *  HttpSession session =
		 * request.getSession(true); session.setAttribute("owner", owner);
		 * session.setAttribute("address", address); session.setAttribute("dog", dog);
		 */

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/registerDog.jsp");
		rd.forward(request, response);
	doGet(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Owner owner = new Owner();
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		// if name -- null creatae error message and go to doGet validation
		String strPhone = request.getParameter("phone");
		String email = request.getParameter("email");
		long phone = Long.parseLong(strPhone);
		
		owner.setFirstName(firstName);
		owner.setLastName(lastName);
		owner.setPhoneNumber(phone);
		owner.setEmail(email);
		
		Dog dog = new Dog();
		String dogName = request.getParameter("dog_name");
		String breed = request.getParameter("breed");
		String foodType = request.getParameter("foodType");
		String feedingAmount = request.getParameter("feedingAmount");
		String sleepingPreference = request.getParameter("sleepingPreference");
		
	//	int disposition = Integer.parseInt(strdisposition);
		int disposition = 1;
		//int zipCode = 12345;
		
		
		dog.setName(dogName);
		dog.setBreed(breed);
		dog.setFoodType(foodType);
		dog.setFeedingAmount(feedingAmount);
		dog.setSleepingPreference(sleepingPreference);
	
		Address address = new Address();
		String street = request.getParameter("street");
		String secondaryAddress = request.getParameter("secondary_address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String strzipCode = request.getParameter("zipcode");
		int zip = 0;
		int count = 0;
		HttpSession session = request.getSession(true);
		if(strzipCode.length() != 5) {
			Info info = new Info("The Zipcode is invalid, must be length of 5","error");
			session.setAttribute("info", info);
			count++;
		}
		try {
		 zip = Integer.parseInt(strzipCode);
		} catch(NumberFormatException nfe) {
			if(count == 0) { // if we dont already have an error
				count++;
				Info info = new Info("The Zipcode is invalid, it must be a number","error");
				session.setAttribute("info", info);
			}
		}
        // use zip for database insert
		// return to screen if count > 0 meaning has error
		if(count > 0) {
			session.setAttribute("owner", owner);
			session.setAttribute("address", address);
			session.setAttribute("dog", dog);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/registerDog.jsp");
			rd.forward(request, response);
			}
		
		
		String straddressType = request.getParameter("addressType");
	//	int addressType = Integer.parseInt(straddressType);
		
		
		address.setStreet(street);
		address.setSecondaryAddress(secondaryAddress);
		address.setCity(city);
		address.setState(state);
		address.setZipCode(zip);
		address.setAddressType(1);
		
		String leash = request.getParameter("offLeash");
		if( (leash != null) &&
				(!(leash.isEmpty()))) {
			dog.setOffLeash(1);
		}
		
	    String waterOk = request.getParameter("waterOk");
		if((waterOk != null) &&
				(!(waterOk.isEmpty()))) {
			dog.setWaterOk(1);	
		}
		
		String walk = request.getParameter("walk");
		if((walk != null) &&
				(!(walk.isEmpty()))) {
			dog.setWalk(1);		
		}
		
		String treats = request.getParameter("treats");
		if((treats != null) &&
				(!(treats.isEmpty()))) {
			dog.setTreats(1);		
		}
		
		String perDay = request.getParameter("perDay");
		if((perDay != null) &&
				(!(perDay.isEmpty()))) {
		int x =	Integer.parseInt(perDay);
			dog.setPerDay(x);		
		}
		
		
		
		
		
		DataStore datastore = new DataStore();
		int i = 0;
		try {
			i = datastore.addOwner(owner);
		}
		catch (Exception e) {
			throw new IOException("databasae error");
		}
	
		try {
			i = datastore.addAddress(address);
		}
		catch (Exception e) {
			throw new IOException("databasae error");
		}
		
		try {
			i = datastore.updateOwner(owner, i);
		}
		catch (Exception e) {
			throw new IOException("databasae error");
		}
		try {
			i = datastore.addDog(dog);
		}
		catch (Exception e) {
			throw new IOException("databasae error");
		}
		try {
			i = datastore.addOwnerDog(owner,dog);
		}
		catch (Exception e) {
			//todo print stacktrace
			if(!(e.getMessage().contains("Duplicate"))) {
				throw new IOException("databasae error");
			}
			}
		// TODO look at Sheps email
			// select address_id from address
			// update owner set address id to ???
//		
//		SELECT * FROM `owner` o  
//		inner join `owner_dog` od on od.owner_id = o.owner_id
//		inner join `dog` d on d.dog_id = od.dog_id
//		WHERE 1
        Info info = new Info("Successful registeration","SUCCESS");
        session.setAttribute("info", info);
    
        
        
		session.setAttribute("owner", owner);
		session.setAttribute("address", address);
		session.setAttribute("dog", dog);
		session.setAttribute("mode", "ADD");	
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/registerDog.jsp");
		rd.forward(request, response);
//		doGet(request, response);
	}

}
