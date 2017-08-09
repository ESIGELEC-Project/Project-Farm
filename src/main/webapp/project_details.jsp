<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="utils/header.jsp">
	<jsp:param value="Project Farm" name="title"/>
</jsp:include>
<%@page import="model.db.*" %>
<%@page import="model.*" %>

	<table border="1" class = "table">
		<tr>
			<th align="center">Acronym</th>
			<th align="center">Category</th>
			<th style="width:10px" align="center"># of incubation days</th>
			<th align="center">Budget</th>
			<th align="center"># of evals</th>
		</tr>
		
		<%if(!ProjectDB.getAllProjects().isEmpty()){ %>
			<%for(Project p:ProjectDB.getAllProjects()) {%>
				<tr>
					<td align="left"><input type="text" name="acronym" value="<%=p.getAcronym() %>" readonly></td>
					<td align="left"><input type="text" name="category" value="<%=p.getCategory().getDescription() %>" readonly></td>
					<td align="left"><input type="text" name="duration" value="<%=p.getFundingDuration() %>" readonly></td>
					<td align="left"><input type="text" name="budget" value="<%=p.getBudget() %>" readonly></td>
					<td align="left"><input type="text" name="evaluations" value="<%=p.getEvaluations().size() %>" readonly></td>
				</tr>
			<%} %>
		<%} %>
	</table>
<script type="text/javascript">
	document.querySelector(".jumbotron").style.backgroundColor = "white";
</script>

<jsp:include page="utils/footer.jsp"></jsp:include>