<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@page import="uic.onlinerealtor.business.RealtorManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function openEditView(assetKey) {
		window
				.open(
						'AdminEdit.jsp?a=' + assetKey,
						'winname',
						'directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=800,height=800');
	}
</script>
</head>
<body>
	<%@ include file="/MasterLoggedIn.jsp"%>
	<%
		UserDetails user = (UserDetails) session
				.getAttribute("userContext");
		if (!(user.getUsertype()==UserType.Admin))
			response.sendRedirect("Login.jsp");
	%>
	<h2>
		<u>ASSET Details</u>
	</h2>
	&nbsp;&nbsp;&nbsp;&nbsp;

	<a href="AdminEdit.jsp">Add New</a>
	<form action="adminview" method="post">
		<%
			RealtorManager realtorMngr = new RealtorManager();
			Hashtable<String, String> assetList = realtorMngr.getAllAssets();
			int propertyCount = 0;
		%>
		<table border="0" width="80%">
			<%
				Enumeration<String> assetIterator = assetList.keys();
			%>
			<%
				while (assetIterator.hasMoreElements()) {
			%>

			<tr>
				<td>
					<div
						style="border-width: 2px; border-style: solid; border-color: black; width: 50%">
						<table border="0" width="50%">
							<tr style="border-bottom: solid 1px black;">
								<td width="5%"># <%
									out.print(" " + (++propertyCount));
								%> <%
 	String asset = assetIterator.nextElement();
 %>
								</td>
								<td width="10%"><a href="#"
									onclick="openEditView('<%out.print(asset);%>')">Update</a></td>
								<td width="80%"><a href="#">Expand</a></td>
							</tr>
							<tr>
								<td colspan="3">
									<%
										out.print(assetList.get(asset));
									%>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<%
				}
			%>

		</table>
	</form>
</body>
</html>