<%@page import="uic.onlinerealtor.entities.City"%>
<%@page import="java.util.List"%>
<%@page import="uic.onlinerealtor.entities.Assets"%>
<%@page import="uic.onlinerealtor.business.RealtorManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/MasterLoggedIn.jsp"%>
	<%
		UserDetails user = (UserDetails) session
				.getAttribute("userContext");
		if (!(user.getUsertype()==UserType.Admin))
			response.sendRedirect("Login.jsp");
	%>
	<form action="Admin" method="post">

		<%
			String assetKey = request.getParameter("a");
			System.out.println("a :" + assetKey);
		%>
		<%
			RealtorManager realtorMgr = new RealtorManager();
			Assets asset = null;
			if (assetKey != null) {
				//System.out.println("a if:" + assetKey);
				asset = realtorMgr.getAssetBasedonKey(assetKey);
			} else {
				//System.out.println("a else:" + assetKey);
				asset = null;
			}
			List<City> cityList = realtorMgr.getCities();
		%>
		<table>
			<tr>
				<td>addressline1</td>
				<td><input type="text" name="addressline1"
					value='<%out.print(asset != null ? asset.getAddressline1() : "");%>' /></td>
			</tr>
			<tr>
				<td>addressline2</td>
				<td><input type="text" name="addressline2"
					value='<%out.print(asset != null ? asset.getAddressline2() : "");%>' /></td>
			</tr>
			<tr>
				<td>city</td>
				<td><select>
						<option value="">--select--</option>
						<%
							for (City city : cityList) {
						%>
						<option
							<%if (city.getCityname().equals(
						asset != null ? asset.getCity() : null))
					out.print("selected='selected'");%>
							value='<%out.print(city.getCityname());%>'>
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
				<td>noofbedrooms</td>
				<td><input type="text" name="noofbedrooms"
					value='<%out.print(asset != null ? asset.getNoofbedrooms() : "");%>' /></td>
			</tr>
			<tr>
				<td>price</td>
				<td><input type="text" name="price"
					value='<%out.print(asset != null ? asset.getPrice() : "");%>' /></td>
			</tr>
			<tr>
				<td>amenities</td>
				<td><input type="text" name="amenities"
					value='<%out.print(asset != null ? asset.getAmenities() : "");%>' /></td>
			</tr>
			<tr>
				<td>typesofrooms</td>
				<td><input type="text" name="typesofrooms"
					value='<%out.print(asset != null ? asset.getTypesofrooms() : "");%>' /></td>
			</tr>
			<tr>
				<td>floortype</td>
				<td><input type="text" name="floortype"
					value='<%out.print(asset != null ? asset.getFloortype() : "");%>' /></td>
			</tr>
			<tr>
				<td>typeofroof</td>
				<td><input type="text" name="typeofroof"
					value='<%out.print(asset != null ? asset.getTypeofroof() : "");%>' /></td>
			</tr>
			<tr>
				<td>fullyfurnished</td>
				<td><input type="text" name="fullyfurnished"
					value='<%out.print(asset != null ? asset.getFullyfurnished() : "");%>' /></td>
			</tr>
			<tr>
				<td>clubmembership</td>
				<td><input type="text" name="clubmembership"
					value='<%out.print(asset != null ? asset.getClubmembership() : "");%>' /></td>
			</tr>
			<tr>
				<td>Estgasbill</td>
				<td><input type="text" name="Estgasbill"
					value='<%out.print(asset != null ? asset.getEstgasbill() : "");%>' /></td>
			</tr>
			<tr>
				<td>Dist from downtown</td>
				<td><input type="text" name="Distfromdowntown"
					value='<%out.print(asset != null ? asset.getDistfromdowntown() : "");%>' /></td>
			</tr>
			<tr>
				<td>ownername</td>
				<td><input type="text" name="ownername"
					value='<%out.print(asset != null ? asset.getOwnername() : "");%>' /></td>
			</tr>
			<tr>
				<td>ownercontact</td>
				<td><input type="text" name="ownercontact"
					value='<%out.print(asset != null ? asset.getOwnercontact() : "");%>' /></td>
			</tr>
			<tr>
				<td>ageofhome</td>
				<td><input type="text" name="ageofhome"
					value='<%out.print(asset != null ? asset.getAgeofhome() : "");%>' /></td>
			</tr>
			<tr>
				<td>section</td>
				<td><input type="text" name="section"
					value='<%out.print(asset != null ? asset.getSection() : "");%>' /></td>
			</tr>
			<tr>
				<td>latitude</td>
				<td><input type="text" name="latitude"
					value='<%out.print(asset != null ? asset.getLatitude() : "");%>' /></td>
			</tr>
			<tr>
				<td>longitude</td>
				<td><input type="text" name="longitude"
					value='<%out.print(asset != null ? asset.getLongitude() : "");%>' /></td>
			</tr>
		</table>
		<input type="submit" value="Submit">
	</form>
</body>
</html>