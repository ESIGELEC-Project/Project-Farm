<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="utils/header.jsp">
	<jsp:param value="Project Farm" name="title"/>
</jsp:include>
<%@ page import= "model.db.*,model.*" %><!-- import classes into the current page -->
<%-- <%String username = session.getAttribute("email").toString(); %>
<%String password = session.getAttribute("password").toString(); %>
<%String name = session.getAttribute("name").toString(); %> --%>
	<form action = "/ProjectFarm/addProjectServlet" method = "post" class="form-horizontal" enctype="multipart/form-data">
		<div>
			<p class="title text-left">New project idea</p>
			
			<div class = "form-group">    
				<label class="col-sm-2 control-label text-right" for="acronym">Title:</label>
	    		<div class="col-sm-10">
	      			<input type="text" class="form-control" placeholder="Acronym of the project" name="acronym" id="acronym" required>
	    		</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label text-right">Description:</label>
	    		<div class="col-sm-10">
	    			<textarea rows="3" class="form-control" placeholder="description of your project" name="description" id="description" required></textarea>
	    		</div>
	  		</div>

		  	<div class="form-group">
		  		<label for="category" class="col-sm-2 control-label text-right">Category: </label>
		  		<div class="col-sm-2">
			  		<select id="category" name="category" class="form-control">
			  			<%for(Category category : CategoryDB.getCategories()) {%>
			  				<option value="<%= category.getDescription()%>"><%=category.getDescription() %></option>
			  				<%} %>
			  		</select>
			  	</div>
			  	
		  		<label for="duration" class="col-sm-2 control-label text-right">Incubation days:</label>
		  		<div class="col-sm-2">
		  			<input type="text" id="duration" name="duration" class="form-control" required>
		  		</div>
		  		
		  		<label for="budget" class="col-sm-2 control-label text-right">Budget(EUR):</label>
		  		<div class="col-sm-2">
		  			<input type="text" id="budget" name="budget" class="form-control" required>
		  		</div>
		  		
		  	</div>
		  	
		  	<div class="form-group">
		  		<label for="documents" class="col-sm-2 control-label text-right">Documents:</label>
		  		<div class="col-sm-10">
		  			<input type="file" name="file" size="50" multiple="multiple" class="form-control" required>
		  		</div>
		  	</div>
		  	
	  	</div>
	  	
	  	<div class="col-md-6 text-center">
	  		<button class="btn btn-primary" type="submit" style="margin:10px;">save</button>
	  	</div>
	  	<div class="col-md-6 text-center">
	  		<button class="btn btn-primary" type="button" id="discard" style="margin:10px;">discard</button>
	  	</div>
	</form>
	<script type = "text/javascript">/* set the background of parent DIV to have no background color */
		var parentDIV = document.querySelector(".jumbotron");
		parentDIV.style.backgroundColor="white";
		$("#discard").click(function(){
			window.location = "/ProjectFarm/RequestMyProjectListServlet";
		});
	</script>
<jsp:include page="utils/footer.jsp"></jsp:include>