<%@page import="java.util.List"%>
<%@page import="uic.onlinerealtor.business.RealtorManager"%>
<%@page import="uic.onlinerealtor.entities.City"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Online Realtor- Sign up!</title>
<script type="text/javascript">
function checkProf(userType) {
	if(userType=='Student'){
		document.getElementById('dvStudent').style.display='block';
		document.getElementById('dvEmployer').style.display='none';		
		document.getElementById('UserAdrressTxt').innerHTML='Please Enter University name';
	}
	else{
		document.getElementById('dvStudent').style.display='none';
		document.getElementById('dvEmployer').style.display='block';		
		document.getElementById('UserAdrressTxt').innerHTML='Please Enter Employer name';
	}
	
}
</script>

</head>
<body style="background-color: #E6E6E6">
	<%@ include file="/Master.jsp" %>

	<form action="signup" method="post">
	<h3><% out.print(request.getAttribute("errormessage")!=null?request.getAttribute("errormessage"):"");%> </h3>
		<%
			RealtorManager realtorMgr = new RealtorManager();
			List<City> cityList = realtorMgr.getCities();
		%>
		<table style="width: 100%; height: 137px;">
			<tr>
				<td  >USERNAME:</td>
				<td ><input name="username"
					value='<%out.print(request.getParameter("username") != null ? request
					.getParameter("username") : "");%>'
					type="text" /></td>
				<td ></td>
			</tr>
			<tr>
				<td >EMAIL ID:</td>
				<td ><input name="email" type="text"
					value='<%out.print(request.getParameter("email") != null ? request
					.getParameter("email") : "");%>' /></td>
				<td ></td>
			</tr>
			<tr>
				<td >CHOOSE PASSWORD:</td>
				<td ><input name="password" type="password" /></td>
				<td></td>
			</tr>
			<tr>
				<td>FIRST NAME:</td>
				<td><input name="Fname" type="text"
					value='<%out.print(request.getParameter("Fname") != null ? request
					.getParameter("Fname") : "");%>' /></td>
				<td ></td>
			</tr>
			<tr>
				<td >LAST NAME:</td>
				<td ><input name="LName" type="text"
					value='<%out.print(request.getParameter("LName") != null ? request
					.getParameter("LName") : "");%>' /></td>
				<td ></td>
			</tr>

			<tr>
				<td >PHONE:</td>
				<td ><input name="Phone" type="text"
					value='<%out.print(request.getParameter("Phone") != null ? request
					.getParameter("Phone") : "");%>' /></td>
				<td ></td>
			</tr>
			<tr>
				<td >Gender :</td>
				<td><input type="radio" name="gender"
					<%out.print(request.getParameter("gender") != null ? request
					.getParameter("gender").equals("M") ? "checked" : "" : "");%>
					value="M"> M<br> <input type="radio" name="gender"
					<%out.print(request.getParameter("gender") != null ? request
					.getParameter("gender").equals("F") ? "checked" : "" : "");%>
					value="F"> F<br></td>
			</tr>

			<tr>
				<td>Profession :</td>
				<td><input type="radio" name="usertype" value="Student" onclick="checkProf(this.value)"
					<%out.print(request.getParameter("usertype") != null ? request
					.getParameter("usertype").equals("Student") ? "checked"
					: "" : "");%>>
					Student<br> <input type="radio" name="usertype" onclick="checkProf(this.value)"
					value="Employee"
					<%out.print(request.getParameter("usertype") != null ? request
					.getParameter("usertype").equals("Employee") ? "checked"
					: "" : "");%>>
					Employee<br> </td>
			</tr>
			<tr>
				<td><span id="UserAdrressTxt"></span> </td>
				<td ><div id="dvEmployer" style="display: none;">
						<input type="text" name="txtEmployer"
							value='<%out.print(request.getParameter("txtEmployer") != null ? request
					.getParameter("txtEmployer") : "");%>'>
					</div>
					<div id="dvStudent" style="display: none;">
						<input type="text" name="txtStUniv"
							value='<%out.print(request.getParameter("txtStUniv") != null ? request
					.getParameter("txtStUniv") : "");%>'>

					</div>
					</td>
			</tr>

			<tr>
				<td >Enter House No:</td>
				<td> <input type="text" name="txtHouseNo"
					value='<%out.print(request.getParameter("txtHouseNo") != null ? request
					.getParameter("txtHouseNo") : "");%>'>
				</td>
			</tr>
			<tr>
				<td >Enter Street :</td>
				<td><input type="text" name="txtStreet"
					value='<%out.print(request.getParameter("txtStreet") != null ? request
					.getParameter("txtStreet") : "");%>'>
				</td>
			</tr>
			<tr>
				<td>City :</td>
				<td><select name="ddlCity">
						<option value="-1">--select--</option>
						<%
							for (City city : cityList) {
						%>
						<option
							<%if (city.getCityname().equals(
						request.getParameter("ddlCity") != null ? request
								.getParameter("ddlCity") : null))
					out.print("selected='selected'");%> value='<%
								out.print(city.getCityId());
							%>'>
							<%
								out.print(city.getCityname());
							%>
						</option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td >Enter ZipCode :</td>
				<td><input type="text" name="txtZipCode"
					value='<%out.print(request.getParameter("txtZipCode") != null ? request
					.getParameter("txtZipCode") : "");%>'>
				</td>
			</tr>

			<tr>
				<td ></td>
				<td><input name="Submit1" type="submit"
					value="Submit" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="Reset1" type="reset" value="Cancel" /></td>
			</tr>

		</table>

	</form>



</body>
</html>