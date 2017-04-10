<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="utils/header.jsp">
	<jsp:param value="ProjectFarm" name="title"/>
</jsp:include>
<%@page import="model.*,model.db.*" %>
<%@page import="java.util.*" %>
<%
String email =  session.getAttribute("email").toString() ;
String name = session.getAttribute("name").toString() ;
String password = session.getAttribute("password").toString();
Evaluator evaluator = new Evaluator(email,name,password);
session.setAttribute("evaluator", evaluator);
					
String owner_email = request.getParameter("owner_email");
String owner_name = request.getParameter("owner_name");
String owner_password = request.getParameter("owner_password");
Owner owner = new Owner(owner_email,owner_name,owner_password);
					
String acronym = request.getParameter("acronym");
					
Project p = ProjectDB.getProject(acronym);  %>
<%List<Document> d = p.getDocuments(); %>
	<div class="container span6 offset3">
	<div class="info-part">
		<p>Project Evaluation:</p>
			
		<div class = "container">
			<div class="col-md-6">Acronym:<%=p.getAcronym() %></div>
			<div class="col-md-6">Created:<%=p.getCreated() %></div>
			<div style="padding:5px;">Description: <%=p.getDescription() %></div>
			<div class="col-md-4">Category: <%=p.getCategory().getDescription() %> </div>
			<div class="col-md-4">Incubation # of days: <%=p.getFundingDuration() %></div>
			<div class="col-md-4">Budget(EUR): <input value=<%=p.getBudget() %> readonly></div>
		</div>
	</div>
	
	<form action="/ProjectFarm/AddEvaluationServlet" method="post">
				<div class="document">
					<p>Documents:</p>
					<%for(Document doc:d){ %>
						<div><%=doc.getDocumentPath() %></div>
					<%} %>
				</div>
				
				<div class="evaluation-part">
					<p>Your evaluation:</p>

					<div class="container">
						<div class="col-md-6 pull-left">
						<label for="attractiveness">Attractiveness:</label>
						<select name="attractiveness">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
						</div>
						<div class="col-md-6 pull-left">
						<label for="risk">Risk level:</label>
						<select name="risk">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
						</div>
					</div>
				</div>
				<input type="hidden" value="<%=acronym%>" name = "acronym" >
				
				
				<span class="col-md-3 text-center">
	  				<input type="submit" value="Save" class="btn btn-primary" id="submit" name="submit" style="margin:10px;">
	  			</span>
	  		<span class="col-md-3 text-center">
	  			<input type="button" value="Discard" class="btn btn-primary" onclick="redirect()" style="margin:10px;">
	 	 	</span>
		</form>
	</div>
<script type="text/javascript">
	document.querySelector(".jumbotron").style.backgroundColor = "white";
	function redirect(){
		window.location = "all_projects.jsp"
	}
</script>
<jsp:include page="utils/footer.jsp"></jsp:include>