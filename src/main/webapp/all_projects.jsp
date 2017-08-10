<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="utils/header.jsp">
	<jsp:param value="ProjectFarm" name="title"/>
</jsp:include>
<%@page import="model.db.*" %>
<%@page import="model.*" %>
<%boolean isempty =  ProjectDB.getAllProjects().isEmpty();%>

	<table border="1" class = "table">
		<tr>
			<th align="center">Acronym</th>
			<th align="center">Category</th>
			<th style="width:10px" align="center"># of incubation days</th>
			<th align="center">Budget</th>
			<th align="center"># of evals</th>
			<th align="center">Action</th>
		</tr>
		
		<%if(!ProjectDB.getAllProjects().isEmpty()){ %>
			<%for(Project p:ProjectDB.getAllProjects()) {%>
				<form action="/ProjectFarm/add_evaluation.jsp" method="post">
				<tr>
					<td align="left"><input type="text" name="acronym" value="<%=p.getAcronym() %>" readonly></td>
					<td align="left"><input type="text" name="category" value="<%=p.getCategory().getDescription() %>" readonly></td>
					<td align="left"><input type="text" name="duration" value="<%=p.getFundingDuration() %>" readonly></td>
					<td align="left"><input type="text" name="budget" value="<%=p.getBudget() %>" readonly></td>
					<td align="left"><input type="text" name="evaluations" value="<%=p.getEvaluations().size() %>" readonly></td>
					<input type="hidden" name="description" value="<%=p.getDescription() %>">
					<input type="hidden" name="owner_name" value="<%=p.getOwner().getName() %>">
					<input type="hidden" name="owner_email" value="<%=p.getOwner().getEmail() %>">
					<input type="hidden" name="owner_password" value="<%=p.getOwner().getPassword() %>">
					<td align="left">
						<input type="submit" value="Evaluate">
					</td>
<%-- 					<!--  href="/ProjectFarm/AddEvaluationServlet?acronym=<%=p.getAcronym() %>&&category=<%=p.getCategory().getDescription()
						%>&&duration=<%=p.getFundingDuration() %>&&owner_name=<%=owner_name %>&&owner_email=<%=owner_email %>
						&&description=<%=p.getDescription() %>&&owner_password=<%=owner_password %>&&budget=<%=p.getBudget()%>"--> --%>
				</tr>
				</form>
			<%} %>
		<%} %>
	</table>


<script type="text/javascript">
	document.querySelector(".jumbotron").style.backgroundColor = "white";
</script>
<jsp:include page="utils/footer.jsp"></jsp:include>