<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="utils/header.jsp">
	<jsp:param value="Home page" name="title"/>
</jsp:include>
<%@page import="model.db.*" %>
		<h2 class="text-center">Project ideas are seeds to change the world</h2>
		<%if(session.getAttribute("email")==null){ %><!--without login  -->
			<button type="submit" class = "btn btn-default center-block btn-lg" onclick = "openPage('project_details.jsp')">Learn more</button>
		<%}else if(UserDB.getEvaluator(session.getAttribute("email").toString()) != null ){ %><!--login as a evaluator  -->
			<button type="submit" class = "btn btn-default center-block btn-lg" onclick = "openPage('all_projects.jsp')">Learn more</button>
		<%}else{ %><!-- login as a owner -->
			<button type="submit" class = "btn btn-default center-block btn-lg" onclick = "openPage('add_project_idea.jsp')">New Project Idea</button>
		<%} %>

<script type="text/javascript">
		function openPage(pageURL){
			window.location.href = pageURL;
		}
</script>
<jsp:include page="utils/footer.jsp"></jsp:include>