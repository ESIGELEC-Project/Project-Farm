<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title><%= request.getParameter("title") %></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
</head>
<body>

<%@page import="model.db.*" %>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="<%= request.getContextPath()%>/index.jsp">ProjectFarm</a>
    </div>
    
    <div>
      	<% if(session.getAttribute("email") == null) { %>
		<form class="navbar-form form-inline pull-right" action="/ProjectFarm/LoginServlet" method="post">
    		<input type="email" class="form-control" placeholder="Email" id="inputEmail3" name="email" required>
    		<input type="password" class="form-control" placeholder="Password" name="password" id="password" required>
    		<button type="submit" class="btn btn-default" id="sign_in">Sign in</button>
    		<% if(request.getAttribute("error") != null) {%>
    		<span id="error_message"><%=request.getAttribute("error") %></span>
    		<%} %>
		</form>
		<% } else { %>
		<div class="dropdown navbar-right nav">
    		<a class="navbar-brand dropdown-toggle" data-toggle="dropdown" href="#"><%= session.getAttribute("name") %></a>
    		<ul class="dropdown-menu">
    		<% %>
    			<%if(UserDB.getOwner(session.getAttribute("email").toString()) != null ){ %>
      			<li><a href="/ProjectFarm/RequestMyProjectListServlet">My projects</a></li>
      			<li><a href="/ProjectFarm/LogoutServlet">Log out</a></li>
      			<%}else{ %>
      			<li><a href="/ProjectFarm/all_projects.jsp">All projects</a></li>
      			<li><a href="/ProjectFarm/LogoutServlet">Log out</a></li>
      			<%} %>
    		</ul>
  		</div>		
		<% } %>
	</div>	    
  </div>
</nav>

<!-- <script type="text/javascript">
	$(document).ready(function(){
		$("#sign_in").click(function(){
			var email = $("#inputEmail3").val();
			var password = $("#password").val();
			$.ajax({
				type:"post",
				data : {
					email:email,
					password:password,
				},
				url:"/ProjectFarm/LoginServlet",
				success:function(result){
					if(result=="email error"||result=="password error"||result=="database error")
						$("#error_message").html(result);
					else
						$(document).html(result);
				}
			});
		});
	});
</script> -->

<div class="container jumbotron vertical-center">

<!-- container, body and HTML tags are still opened -->