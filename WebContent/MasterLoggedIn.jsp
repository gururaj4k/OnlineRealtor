<%@page import="uic.onlinerealtor.entities.UserPreference.UserType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="uic.onlinerealtor.entities.UserDetails"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Realtor</title>
<script type="text/javascript">
	function CheckLoginStatus() {
		document.loginform.submit();
	}
</script>
</head>
<body style="background-color: #E6E6E6">
	<form action="loginStatus" method="post" name="loginform">
		<%
			if (session.getAttribute("userContext") == null)
				response.sendRedirect("Login.jsp");
		%>
		<table width="100%" >
			<tr>
				<td width="55%" align="right">
					<h2>
						<u>ONLINE REALTOR</u>
					</h2>
				</td>
				<td width="20%"></td>
				<td align="right">
					<table  width="100%">
						<tr>
							<td align="right">Hello</td>
							<td>
								<%
									UserDetails userDet = null;
									if (session.getAttribute("userContext") != null) {
										userDet = (UserDetails) session.getAttribute("userContext");
										out.print(" <b><h3>" + userDet.getFirstname() + "!    </h3></b>");
									}
								%>
							</td>
						</tr>
					</table>
				</td>
				<td align="right">
					<%
						if (!(userDet.getUsertype()==UserType.Admin))
							out.print("<a href='Home.jsp'>Home</a>");
					%>&nbsp;&nbsp;&nbsp; <a href="#" onclick="CheckLoginStatus()"> <%
 	if (userDet != null)
 		out.print("Logout");
 	else
 		out.print("Login");
 %>
				</a>
				</td>

			</tr>

		</table>
	</form>
</body>

</html>