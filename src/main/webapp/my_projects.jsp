<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="utils/header.jsp">
	<jsp:param value="Project Farm" name="title"/>
</jsp:include>
<%@page import="model.*,model.db.*,java.util.List" %>
<% 
String email = session.getAttribute("email").toString();
String name = session.getAttribute("name").toString();
String password = session.getAttribute("password").toString();
Owner owner = new Owner(email,name,password);
List<Project> myProject = ProjectDB.getProjectsOfOwner(owner);
%>
	<table border="1" class = "table">
		<tr>
			<th align="center">Acronym</th>
			<th align="center">Category</th>
			<th style="width:10px" align="center"># of incubation days</th>
			<th align="center">Budget</th>
			<th align="center">Risk Level</th>
			<th align="center">Attractiveness</th>
			<th align="center"># of evaluators</th>
		</tr>
		<%if(!myProject.isEmpty()){ %>
			<%for(Project p:myProject) {%>
			<%double risk=0;%>
			<%double attractiveness=0; %>
			<%if(p.getEvaluations().size() != 0){
				double sum_risk = 0;
				double sum_attr = 0;
				for(Evaluation e:p.getEvaluations()){ 
					sum_risk += e.getRiskLevel();
					sum_attr += e.getAttractiveness();
				}
				risk = sum_risk/p.getEvaluations().size();
				attractiveness = sum_attr/p.getEvaluations().size();
			}%>
			<tr>
				<td align="left">
				<a href="/ProjectFarm/GetProjectDetailServlet?acronym=<%=p.getAcronym()%>"><%=p.getAcronym() %></a><!--this is a very important way to send data  -->
				</td>
				<td align="left"><%=p.getCategory().getDescription() %></td>
				<td align="left"><%=p.getFundingDuration() %></td>
				<td align="left"><%=p.getBudget() %></td>
				<td align="left"><%=risk %></td>
				<td align="left"><%=attractiveness %></td>
				<td align="left"><%=p.getEvaluations().size() %></td>
			</tr>
			<%} %>
		<%} %>
		</table>
	<script type="text/javascript">
		document.querySelector(".jumbotron").style.backgroundColor="white";
	</script>
<jsp:include page="utils/footer.jsp"></jsp:include>