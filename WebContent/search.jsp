<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="ISO-8859-1">
 <!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<title>Insert title here</title>
<style>
.container{ 
    max-width: 40%;
    padding: 10px;
    margin: auto;
 }
 
</style>
</head>
<body onload="init()">
<nav class="nav-wraper indigo">
 <a href="#!" class="brand-logo center"> Search Dogs </a>
</nav>
<div class="container">
<font size="5"  face="verdana" color="blue"> Search by Owner </font>
<div class="row">
<form action="search" method= "post" id="searchForm" >
<div class="col s12 l6 ">
 Owner Name :
   <input id="name" type="text" name="name " value="">
</div>
<div class=" col s12 l6">
 Email :
   <input id="name" type="email" name="email" size="40">
</div>
<div class="col s6">
Phone Number :
   <input id="phone" type="text" name="phone" size="40">
</div>
  </form>
</div>
</div>
<div class="container">
<font size="5"  face="verdana" color="blue"> Search by Dog </font>
<div class="row">
<form action="search" method= "post" id="searchForm" >
<br>
<div class="col s6">
 Dog's Name :
   <input id="dog_name" type="text" name="dog_name" value="" size="40">
</div>
<div class="col s6"> 
<div class="col s6">
<input  type="submit" name="search" value="Search" class="btn indigo">
 </div>
</div>
  </form>
</div>
</div>
<div class="container">
<c:forEach var="owner" items="${ownerList}">
	<form action="registration" method="get" >
	<div id="data">
		<div class="row">
		 <div class="col s12 l5">
			 <font size="4"  face="verdana" color="blue">Owner :</font>&nbsp;&nbsp;&nbsp;&nbsp;
			 ${owner.firstName} ${owner.lastName}<br><br>
			 <font size="4"  face="verdana" color="blue">Dog :</font>&nbsp;&nbsp;&nbsp;&nbsp;
				${owner.dog.name}&nbsp;&nbsp;<br><br> 
			 <font size="4"  face="verdana" color="blue"> Email : </font>&nbsp;&nbsp;&nbsp;&nbsp;
				${owner.email}<br><br>
			 <font size="4"  face="verdana" color="blue">Phone :</font>&nbsp;&nbsp;&nbsp;&nbsp;
				${owner.phoneNumber} &nbsp;&nbsp; 
		  </div>
        </div>
	</div>
	 <div >
		<input type="submit" name="edit details" value="Edit Details" class="btn indigo">&nbsp;&nbsp;&nbsp;
		<input type="submit" name="schedule visit" value="Schedule Visit" class="btn indigo">&nbsp;&nbsp;&nbsp;
		<input type="submit" name="delete" value="Delete" class="btn indigo">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="text" hidden name="ownerId" value="${owner.id}">
		<input type="text" hidden name="dogId" value="${owner.dog.id}">
			         <!-- 2 hidden inputs for owner id and dog id --> 
	</div>
		</form>
</c:forEach>
 </div>
<script>
function init() {
	debugger;
  //var text = "";
  //document.getElementById("address").innerHTML = "initial address from javascript";
  document.getElementById("name").value = "";
  document.getElementById("email").value = "";
  document.getElementById("phone").value = "";
  document.getElementById("dog_name").value = "";
  
}
function init2() {
	debugger;
  //var text = "";
  document.getElementById("data").innerHTML = "";
  
}
jQuery('#datetimepicker').datetimepicker();
</script>

</body>
</html>
