<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<form action="main" method="post"> 
    <input type="submit" name="register" value="Register Dog" class="btn indigo"> 
    <br>
    <br>
     <input type="submit" name="search" value="Search" class="btn indigo"> 
     <br>
    <br>
     <input type="submit" name="scheduleVisit" value="Schedule Visit" class="btn indigo"> 
     <br>
    <br>
     <input type="submit" name="currentDogs" value="Current Dogs" class="btn indigo"> 
    <br>
    <br>
    <span>From Date: </span><input type ="text" name="fromDate" value="${currdate}">
   </form>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
  <script>
	  $(document).ready(function() {
		  var date = $('#fromDate').val();
		  $.get('Appointments', {
              fromDate : date
	      }, function(data) {
	    	  processData(data);
	      });
		});
		
	  function processData(data){
		  // data is json object 
		  var result1 = data.items[1]; // JSONArray "items" containing JSONArray of DogAppointment objects
	  	  var dogAppts = result1.DogAppointments; // JSONArray of "DogAppointment" object
	  	  
		  // todo ... build html table (see article)
		  // reference dogAppts[index]
	  }
 </script>
</body>
</html>
