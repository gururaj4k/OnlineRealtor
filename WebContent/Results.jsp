
<%@page language="java" import="java.util.*" %>
<%@page import="uic.onlinerealtor.entities.Assets" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <!--%@ page import="uic.onlinerealtor.entities.Assets" %> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" language="javascript">
  function getUsersfroFB(city) {
	//alert('city in results.. '+city);
	window.open('FB.jsp?c='+city,'winname','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=400,height=350');
	//window.open("FB.jsp?c="+city);
}
  function openMaps(latlon,address1,address2) {	
	  window.open('GMaps.jsp?pos='+latlon+'&ad2='+address2+'&ad1='+address1,'winname','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=500,height=500');
		
	}
  
</script>

<title>Insert title here</title>
</head>
<body>
<%@ include file="/MasterLoggedIn.jsp" %>
<form action="results" method="post">
		
				
			<%List<Assets> assests1 = (List<Assets>)request.getAttribute("results1");%>
			<%int propertyCount=1;%>
			
<h3> <label id="message"><% out.print(request.getAttribute("message")!=null?request.getAttribute("message"):"");%> </label></h3>
<table border="1">
<% 
if(assests1.size()==0)
out.print("No Results found..");
for (Assets o : assests1) { %>

 <tr >
                <td>
                    <table border="0" >
                        <tr>
                            <td colspan="2">
                               <b><u> Property <%out.print(propertyCount);%></u> </b>:
                            </td>
                            <td>
                            <input type="checkbox" name="markFav"  value="<%out.print(o.getAssetKey()); %>">Mark as Favorite<br>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b> Address :</b>
                            </td>
                            <td>
                               <%out.print(o.getAddressline1()); %>, <%out.print(o.getAddressline2()); %>
                            </td>
                            <td align="right">
                                <b> Price :</b>
                            </td>
                            <td align="left">
                               <%out.print(o.getPrice());%> $
                            </td>
                        </tr>
                         <tr>
                            <td>
                                <b> Owner Name :</b>
                            </td>
                            <td>
                                <%out.print(o.getOwnername());%>
                            </td>
                            <td>
                              <b>   Owner Number :</b>
                            </td>
                            <td>
                                2069158154
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                            <%String latlong= o.getLatitude()+","+o.getLongitude(); %>
                                <a href="#"  onclick="openMaps('<%=latlong %>','<%=o.getAddressline1()%>','<%=o.getAddressline2() %>')">Show on GMap</a>
                            </td>
                            <td colspan="2">
                               <a href="#" onclick="getUsersfroFB('<% out.print(o.getCity());%>')">Look for Friends on FB</a>
                            </td>
                        </tr>
                    </table>
                </td>
                
            </tr>
            <%propertyCount++; %>

<% } %>	

</table>
<% 
if(assests1.size()!=0)
	out.print("<input type='submit'  value='Save the Favourites'>");
                            		   %>
			
	</form>
</body>
</html>