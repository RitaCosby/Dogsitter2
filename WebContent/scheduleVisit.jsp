<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- Compiled and minified CSS -->
<link rel="stylesheet" 
       href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.datetimepicker.min.css"/ >
<script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.datetimepicker.full.min.js"></script>

<title>Insert title here</title>
<style>
 .container{ 
    max-width: 40%;
    padding: 10px;
    margin: 23px;
    margin: auto;
 } 
</style>
</head>
<body>
<nav class="nav-wraper indigo">
 <a href="#!" class="brand-logo center"> Visitation </a>
</nav>
<h6 style= text-align:center>To schedule a visit complete the following form with all required fields.</h6>
<div class="container">
<font size="5"  face="verdana" color="blue"> Schedule </font>
<div class="row">
<form action="ScheduleVisit" method= "post" ><br>
<div class="col s12 l6">
 Owner Name :
   <input id="owner_name" type="text" name=" " value=""><br><br>
    <span> From Date</span><input id="datetimepicker1" type="text" placeholder="Click" >
   <span>To Date </span><input id="datetimepicker2" type="text" placeholder="Click" >  
   <input type="submit" name="sumbit" value="Submit" class="btn indigo"> 
</div>

<div class="col s12 l6">
             <div class="input-field">
             <select id="state" name="state">
               <option value="" selected>Dogs</option>
               <option value="PA">PA</option>
               <option value="MD">MD</option>
               <option value="VA">VA</option>
               <option value="NY">NY</option>
               <option value="WV">WV</option>
              </select>
             </div><br>
         <span> Drop Off Time</span><input id="datetimepicker3" type="text" placeholder="Click" >  
         <span> Pick Up Time</span><input id="datetimepicker4" type="text" placeholder="Click" >  
   </div>
  </form>
</div>
</div>
    <script>
        $(document).ready(function(){
            $('select').formSelect();
        });
          jQuery('#datetimepicker1').datetimepicker({
        	  timepicker:false
          });  
          jQuery('#datetimepicker2').datetimepicker({
        	   timepicker:false
          }); 
          jQuery('#datetimepicker3').datetimepicker({
       	       datepicker:false
         });  
          jQuery('#datetimepicker4').datetimepicker({
       	      datepicker:false
         });  
      </script>
</bod>
 <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</html>