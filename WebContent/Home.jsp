<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function loadQuestions() {
	document.homeForm.submit();
}
</script>
</head>
<body>
<%@ include file="/MasterLoggedIn.jsp" %>
<form action="questions" method="post" name="homeForm">
<%session.setAttribute("currentQuestion",null);
session.setAttribute("UserPreference", null);
%>
<h2>Please select the any option. </h2>
	<table>
		<tr>

			<td><h2>
					<a href="Favourties.jsp">View Favourties</a>
				</h2></td>

		</tr>

		<tr>

			<td><h2>
					<a href="#" onclick="loadQuestions()">Search for Properties</a>
				</h2></td>

		</tr>
	</table>
	</form>
</body>
</html>