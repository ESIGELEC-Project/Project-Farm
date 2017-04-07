<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <style>
    .info-part,.upload-part,.statistics-part{
    	border:1px solid grey;
    	margin-top:10px;
    	margin-bottom:10px;
    }
    .myFile {
	  position: relative;
	  overflow: hidden;
	  float: left;
	  clear: left;
	  border:1px solid #AAA;
	  border-radius:4px;
	}
	.myFile input[type="file"] {
	  display: block;
	  position: absolute;
	  top: 0;
	  right: 0;
	  opacity: 0;
	  font-size: 100px;
	  filter: alpha(opacity=0);
	  cursor: pointer;
	}
    
	</style>
<jsp:include page="utils/header.jsp">
	<jsp:param value="Project Farm" name="title"/>
</jsp:include>
<%@page import="model.*,model.db.*" %>
<%@page import="java.io.*" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%
Project p = (Project)session.getAttribute("project");
String acronym = p.getAcronym(); 
session.setAttribute("acronym", acronym);
String description = p.getDescription();
String category = p.getCategory().getDescription();
int duration = p.getFundingDuration();
double budget = p.getBudget();
int evaluators = p.getEvaluations().size();
double sum_risk = 0;
double sum_attractiveness = 0;
for(Evaluation e: p.getEvaluations()){
	sum_risk+=e.getRiskLevel();
	sum_attractiveness += e.getAttractiveness();
}
double risk;
double attractiveness;
if(evaluators!=0){
	risk = sum_risk/evaluators;
	attractiveness = sum_attractiveness/evaluators;
}else{
	risk=0;
	attractiveness=0;
}
List<Document> files = ProjectDB.getProject(acronym).getDocuments();
%>

<div class="container span6 offset3">
	<div class="info-part">
		<p style="padding:5px;">Project Evaluation:</p>
			
		<div class = "container">
			<div class="col-md-6">Acronym:  <%=acronym%></div>
			<div class="col-md-6">Created at:  <%=p.getCreated() %></div>
			<div style="padding:5px;">Description:  <%=description %></div>
			<div class="col-md-4">Category:  <%=category %> </div>
			<div class="col-md-4">Incubation # of days:  <%=duration %></div>
			<div class="col-md-4">Budget(EUR):  <input value=<%=budget %> readonly></div>
		</div>
	</div>
	
	<form action="/ProjectFarm/addDocumentServlet" method="post" enctype="multipart/form-data">
		<div class="upload-part" style="padding:5px;">
				<p style="padding:5px;">Documents: </p>

				<div class="container">
				<%if(!files.isEmpty()) {%>
					<%for(int i=0;i<files.size();i++){ %>
					<div class="pull-left"><%=files.get(i).getDocumentPath() %></div>
					<%} %>
				<%} %>
				<label class="myfile  pull-right">
					<input id="file" type="file" name="file" size="50" class="btn upload">
					<span>Browse file</span>
				</label>
				</div>
		</div>
		
		<div class="statistics-part" style="padding:5px;">
			<p style="padding:5px;">Statistics:</p>
			
			<div class="container">
				<div class="col-md-4">Risk level: <%= risk%></div>
				<div class="col-md-4">Attractiveness: <%=attractiveness %></div>
				<div class="col-md-4"># of evaluators: <%=evaluators %></div>
			</div>
		</div>
		<span class="col-md-3 text-center">
  			<input type="submit" value="Save" class="btn btn-primary" id="submit" name="submit" style="margin:10px;">
  		</span>
  		<span class="col-md-3 text-center">
  			<input type="button" value="Discard" class="btn btn-primary" onclick="discard()" style="margin:10px;">
 	 	</span>
	</form>
</div>

<script type="text/javascript">
	document.querySelector(".jumbotron").style.backgroundColor="white";
	$(document).ready(function(){
		var intervalFunc = function(){
			$("#file").html($('#file').val());
		};
		$("#save").on("click",function(){
			$("#file").click();
			setInterval(interval,1);
			return false;
		});
	});
	function discard(){
		window.location = "my_projects.jsp"
	}
	
</script>
<jsp:include page="utils/footer.jsp"></jsp:include>