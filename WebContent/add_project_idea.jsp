<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="utils/header.jsp">
	<jsp:param value="Project Farm" name="title"/>
</jsp:include>
<%@ page import= "model.db.*,model.*" %><!-- import classes into the current page -->
<%-- <%String username = session.getAttribute("email").toString(); %>
<%String password = session.getAttribute("password").toString(); %>
<%String name = session.getAttribute("name").toString(); %> --%>
	<form action = "/ProjectFarm/AddProjectServlet" method = "post" class="form-horizontal" style="">
		<div>
			<p class="title text-left">New project idea</p>
			<div class = "form-group">
			<label class="col-sm-2 control-label text-right" for="acronym">Title:</label>
	    		<div class="col-sm-8">
	      			<input type="text" class="form-control" placeholder="Acronym of the project" name="acronym" id="acronym" required>
	    		</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Description:</label>
	    		<div class="col-sm-8">
	    			<textarea rows="3" class="form-control" placeholder="description of your project" name="description" id="description" required></textarea>
	    		</div>
	  		</div>
	  	</div>
	  	
	  	<div class="form-inline text-center" style="">
	  		<label for="category" style="margin:5px;">Category: </label>
	  		<select id="category" name="category" class="form-control">
	  			<%for(Category category : CategoryDB.getCategories()) {%>
	  				<option value="<%= category.getDescription()%>"><%=category.getDescription() %></option>
	  				<%} %>
	  		</select>
	  		<label for="duration" style="margin:5px;">Incubation # of days:</label>
	  		<input type="text" id="duration" name="duration" class="form-control" required>
	  		<label for="budget" style="margin:5px;">Budget(EUR):</label>
	  		<input type="text" id="budget" name="budget" class="form-control" required>
	  	</div>
	  	<span class="col-md-3 text-center">
	  		<button class="btn btn-primary" type="submit" style="margin:10px;">save</button>
	  	</span>
	  	<span class="col-md-3 text-center">
	  		<button class="btn btn-primary" type="button" id="discard" style="margin:10px;">discard</button>
	  	</span>
	</form>
	<script type = "text/javascript">/* set the background of parent DIV to have no background color */
		var parentDIV = document.querySelector(".jumbotron");
		parentDIV.style.backgroundColor="white";
		$("#discard").click(function(){
			window.location = "/ProjectFarm/RequestMyProjectListServlet";
		});
	</script>
<jsp:include page="utils/footer.jsp"></jsp:include>