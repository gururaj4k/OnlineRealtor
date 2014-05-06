<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="uic.onlinerealtor.entities.Question" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/MasterLoggedIn.jsp" %>
	<form action="question" method="post">
	<h2> Please answer the following Questions</h2>
		<div align="center" style="margin-top: 250px" >
			<div id="questionContainer" name="questionContainer" style="border:1px solid black;width: 500px">
				<%Question currentQuestion = (Question) session.getAttribute("currentQuestion");
			System.out.println("This is  the test page.. " + currentQuestion.getQuestion());
			out.print(currentQuestion.getQuestion());
			System.out.println("Current QuesiotnID :"+currentQuestion.getQuestionId()); %>
			<input type="hidden" id="hdnQuesID" name="hdnQuesID" value="<%out.print(String.valueOf(currentQuestion.getQuestionId())); %>">
			</div>
			<input type="submit" value="Submit" >
		</div>
	</form>
</body>
</html>