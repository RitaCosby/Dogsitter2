package com.dogsitter.dal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.dogsitter.model.Address;
import com.dogsitter.model.Dog;
import com.dogsitter.model.Owner;

public class DataStore {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private String host = "localhost";
	private String user = "Rita";
	private String passwd = "1234";
	private String database = "dogsitter";

	
	public int addOwner(Owner owner) throws Exception {
		
		Statement statement = getStatement();

		StringBuffer sb = new StringBuffer();
		sb.append("insert into dogsitter.owner ");
		sb.append(" ( `first_name`,`last_name`, `phone`, `email`) ");
		sb.append(" values ( '");
		sb.append(owner.getFirstName());
		sb.append("', '");
		sb.append(owner.getLastName());
		sb.append("', ");
		sb.append(owner.getPhoneNumber());
		sb.append(", '");
		sb.append(owner.getEmail());
		sb.append("' )");

		String strSql = sb.toString();
		int count = statement.executeUpdate(strSql);

		sb.delete(0, sb.length());
		sb.append(" select owner_id from dogsitter.owner ");
		sb.append(" where first_name =  '");
		sb.append(owner.getFirstName());
		sb.append("' and last_name =  '");
		sb.append(owner.getLastName());
		sb.append("'");

	
		
		 strSql = sb.toString();
	     resultSet = statement.executeQuery(strSql);
	     if(resultSet.next()){
	    	 int ownerId = resultSet.getInt(1);
	    	 owner.setId(ownerId);
	     }

		
		
		return count;

	}
	
	public int addDog(Dog dog) throws Exception {
		
		Statement statement = getStatement();

		StringBuffer sb = new StringBuffer();
		sb.append("insert into dogsitter.dog ");
		sb.append(" ( `name`, `breed`,`off_leash_ok`,`dog_water_ok`,`walk_to_do_business`,`treats`,`treats_perday`,`food_type`,`food_amount_cups`,`sleeping_preference`) ");
		sb.append(" values ( '");
		sb.append(dog.getName());
		sb.append("', '");
		sb.append(dog.getBreed());
		sb.append("', '");
		sb.append(dog.getOffLeash());
		sb.append("', '");
		sb.append(dog.getWaterOk());
		sb.append("', '");
		sb.append(dog.getWalk());
		sb.append("', '");
		sb.append(dog.getTreats());
		sb.append("', ");
		sb.append(dog.getPerDay());
		sb.append(", '");
		sb.append(dog.getFoodType());
		sb.append("', '");
		sb.append(dog.getFeedingAmount());
		sb.append("', '");
		sb.append(dog.getSleepingPreference());
		sb.append("' )");

		String strSql = sb.toString();
		int count = statement.executeUpdate(strSql); 
		
		sb.delete(0, sb.length());
		sb.append(" select dog_id from dogsitter.dog ");
		sb.append(" where name =  '");
		sb.append(dog.getName());
		sb.append("' and breed =  '");
		sb.append(dog.getBreed());
		sb.append("'");

	
		
		 strSql = sb.toString();
	     resultSet = statement.executeQuery(strSql);
	     if(resultSet.next()){
	    	 int dogId = resultSet.getInt(1);
	    	 dog.setId(dogId);
	      }
	     return count;
	}
	
	public int addAddress(Address address) throws Exception {
		Statement statement = getStatement();

		StringBuffer sb = new StringBuffer();
		sb.append("insert into dogsitter.address ");
		sb.append(" ( `street`, `secondary_address`,`city`,`state`, `zip`, `address_type` ) ");
		sb.append(" values ('");
		sb.append(address.getStreet());
		sb.append("','");
		sb.append(address.getSecondaryAddress());
		sb.append("','");
		sb.append(address.getCity());
		sb.append("', '");
		sb.append(address.getState());
		sb.append("', ");
        sb.append(address.getZipCode());
		sb.append(", ");
		sb.append(address.getAddressType());
		sb.append(" )");

		String strSql = sb.toString();
		int count = statement.executeUpdate(strSql);
        int addressId = 0;
        
        statement = connect.createStatement();
		// Result set get the result of the SQL query

		sb.delete(0, sb.length());
		sb.append(" select address_id from dogsitter.address ");
		sb.append(" where street =  '");
		sb.append(address.getStreet());
		sb.append("' and zip =  ");
		sb.append(address.getZipCode());

	
		
		 strSql = sb.toString();
	     resultSet = statement.executeQuery(strSql);
	     if(resultSet.next()){
	    	 addressId = resultSet.getInt(1);
	     	}
		return addressId;
		
	}
	
	public int updateOwner(Owner owner, int addressId) throws Exception {
		   
	   int count = 0;
	   Statement statement = getStatement();

		StringBuffer sb = new StringBuffer();
		sb.append("update dogsitter.owner ");
		sb.append(" set `address_id`=  ");
		sb.append(addressId);
		sb.append(" where email= '");
		sb.append(owner.getEmail());
		sb.append("'");
		

		String strSql = sb.toString();
		count = statement.executeUpdate(strSql);
        return count;
		}
	
	public int addOwnerDog(Owner owner, Dog dog)  throws Exception {

		// Statements allow to issue SQL queries to the database
		Statement statement = getStatement();
		// Result set get the result of the SQL query

		StringBuffer sb = new StringBuffer();
		sb.append("insert into dogsitter.owner_dog ");
		sb.append(" ( `owner_id`, `dog_id`) ");
		sb.append(" values ( ");
		sb.append(owner.getId());
		sb.append(",");
		sb.append(dog.getId());
		sb.append(" )");

		String strSql = sb.toString();
		int count = statement.executeUpdate(strSql);
		
		return count;
		}
	
	
	public ArrayList<Owner> findOwnerByName(String fn, String ln) throws Exception{
		Statement statement = getStatement();
		StringBuffer sb = new StringBuffer();
		sb.append(" select first_name,last_name, phone, email, name, o.owner_id, d.dog_id ");
		sb.append(" From dogsitter.owner o");
		sb.append(" INNER JOIN dogsitter.owner_dog od ON o.owner_id  = od.owner_id");
	    sb.append(" INNER JOIN dogsitter.dog d ON od.dog_id = d.dog_id");
		sb.append(" where 1 = 1 ");
		if(fn != null) {
		    sb.append(" and first_name =  '");
			sb.append(fn);
		}
		if(ln != null) {
			sb.append("' and last_name =  '");
			sb.append(ln);
		}
        sb.append("';");
		
		String strSql = sb.toString();
	    resultSet = statement.executeQuery(strSql);
	    ArrayList<Owner> owners = new ArrayList<Owner>();
	    while(resultSet.next()) {
	    	Owner o = new Owner();
	    	o.setFirstName(resultSet.getString(1));
	    	o.setLastName(resultSet.getString(2));
	    	o.setPhoneNumber(resultSet.getLong(3));
	    	o.setEmail(resultSet.getString(4));
	    	Dog d = new Dog();
	    	d.setName(resultSet.getString(5));
	    	o.setId(resultSet.getInt(6));
	    	d.setId(resultSet.getInt(7));
	    	o.setDog(d);
	    	owners.add(o);
	    	}
	    
	   return owners;
	}
	public ArrayList<Owner> findOwnerByEmail(String email) throws Exception{
		Statement statement = getStatement();
		StringBuffer sb = new StringBuffer();
		sb.append(" select first_name,last_name, phone, email, name, o.owner_id, d.dog_id");
		sb.append(" From dogsitter.owner o");
		sb.append(" INNER JOIN dogsitter.owner_dog od ON o.owner_id  = od.owner_id");
	    sb.append(" INNER JOIN dogsitter.dog d ON od.dog_id = d.dog_id");
		sb.append(" where email like  '");
		sb.append(email);
        sb.append("%';");
		
		String strSql = sb.toString();
	    resultSet = statement.executeQuery(strSql);
	    ArrayList<Owner> owners = new ArrayList<Owner>();
	    while(resultSet.next()) {
	    	Owner o = new Owner();
	    	o.setFirstName(resultSet.getString(1));
	    	o.setLastName(resultSet.getString(2));
	    	o.setPhoneNumber(resultSet.getLong(3));
	    	o.setEmail(resultSet.getString(4));
	    	Dog d = new Dog();
	    	d.setName(resultSet.getString(5));
	    	o.setId(resultSet.getInt(6));
	    	d.setId(resultSet.getInt(7));
	    	o.setDog(d);
	    	owners.add(o);
	    	}
	    
	   return owners;
	}
	public ArrayList<Owner> findOwnerByPhone(int phone) throws Exception{
		Statement statement = getStatement();
		StringBuffer sb = new StringBuffer();
		sb.append(" select first_name,last_name, phone, email, name, o.owner_id, d.dog_id ");
		sb.append(" From dogsitter.owner o");
		sb.append(" INNER JOIN dogsitter.owner_dog od ON o.owner_id  = od.owner_id");
	    sb.append(" INNER JOIN dogsitter.dog d ON od.dog_id = d.dog_id");
		sb.append(" where phone like  '");
		sb.append(phone);
        sb.append("%';");
		
		String strSql = sb.toString();
	    resultSet = statement.executeQuery(strSql);
	    ArrayList<Owner> owners = new ArrayList<Owner>();
	    while(resultSet.next()) {
	    	Owner o = new Owner();
	    	o.setFirstName(resultSet.getString(1));
	    	o.setLastName(resultSet.getString(2));
	    	o.setPhoneNumber(resultSet.getLong(3));
	    	o.setEmail(resultSet.getString(4));
	    	Dog d = new Dog();
	    	d.setName(resultSet.getString(5));
	    	o.setId(resultSet.getInt(6));
	    	d.setId(resultSet.getInt(7));
	    	
	    	o.setDog(d);
	    	owners.add(o);
	    	}
	    
	   return owners;
	}
	public ArrayList<Owner> findOwnerByDogN(String name) throws Exception{
		Statement statement = getStatement();
		StringBuffer sb = new StringBuffer();
		sb.append(" select first_name,last_name, phone, email, name, o.owner_id, d.dog_id ");
		sb.append(" From dogsitter.owner o");
		sb.append(" INNER JOIN dogsitter.owner_dog od ON o.owner_id  = od.owner_id");
	    sb.append(" INNER JOIN dogsitter.dog d ON od.dog_id = d.dog_id");
		sb.append(" where d.name like  '");
		sb.append(name);
        sb.append("%';");
		
		String strSql = sb.toString();
	    resultSet = statement.executeQuery(strSql);
	    ArrayList<Owner> owners = new ArrayList<Owner>();
	    while(resultSet.next()) {
	    	Owner o = new Owner();
	    	o.setFirstName(resultSet.getString(1));
	    	o.setLastName(resultSet.getString(2));
	    	o.setPhoneNumber(resultSet.getLong(3));
	    	o.setEmail(resultSet.getString(4));
	    	Dog d = new Dog();
	    	d.setName(resultSet.getString(5));
	    	o.setId(resultSet.getInt(6));
	    	d.setId(resultSet.getInt(7));
	    	o.setDog(d);
	    	owners.add(o);
	    	}
	    
	   return owners;
	}
	public Owner editDetails(Integer ownerId, Integer dogId) throws Exception{
		Statement statement = getStatement();
		StringBuffer sb = new StringBuffer();
		sb.append(" select first_name,last_name, phone, email,street,secondary_address,city,state,zip,name,off_leash_ok,dog_water_ok,walk_to_do_business,treats,treats_perday,"
				+ "food_type,food_amount_cups,sleeping_preference,medicine_info,o.owner_id,d.dog_id,a.address_id ");
		sb.append(" From dogsitter.owner o");
		sb.append(" INNER JOIN dogsitter.owner_dog od ON o.owner_id  = od.owner_id");
	    sb.append(" INNER JOIN dogsitter.dog d ON od.dog_id = d.dog_id");
	    sb.append(" INNER JOIN dogsitter.address a ON o.address_id = a.address_id");
		sb.append(" where o.owner_id=   ");
		sb.append(ownerId);
		sb.append(" And d.dog_id=  ");
		sb.append(dogId);
        sb.append(";");
		
		String strSql = sb.toString();
	    resultSet = statement.executeQuery(strSql);
	    Owner o = new Owner();
	    while(resultSet.next()) {
	    	o.setFirstName(resultSet.getString(1));
	    	o.setLastName(resultSet.getString(2));
	    	o.setPhoneNumber(resultSet.getLong(3));
	    	o.setEmail(resultSet.getString(4));
	    	Address a = new Address();
	    	a.setStreet(resultSet.getString(5));
	    	a.setSecondaryAddress(resultSet.getString(6));
	    	a.setCity(resultSet.getString(7));
	    	a.setState(resultSet.getString(8));
	    	a.setZipCode(resultSet.getInt(9));
	    	o.setAddress(a);
	    	Dog d = new Dog();
	    	d.setName(resultSet.getString(10));
	    	d.setOffLeash(resultSet.getInt(11));
	    	d.setWaterOk(resultSet.getInt(12));
	    	d.setWalk(resultSet.getInt(13));
	    	d.setTreats(resultSet.getInt(14));
	    	d.setPerDay(resultSet.getInt(15));
	    	d.setFoodType(resultSet.getString(16));
	    	d.setFeedingAmount(resultSet.getString(17));
	    	d.setSleepingPreference(resultSet.getString(18));
	    	d.setMedicineNotes(resultSet.getString(19));
	    	o.setId(resultSet.getInt(20));
	    	d.setId(resultSet.getInt(21));
	    	a.setAddressId(resultSet.getInt(22));
	    	o.setDog(d);
	    }
	    
	   return o;
	}

	
	
	
	private Statement getStatement() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		// Setup the connection with the DB
		connect = DriverManager
				.getConnection("jdbc:mysql://" + host + "/" + database + "?" + "user=" + user + "&password=" + passwd);

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		return statement;
	}
	
}

