<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function openSignup(){
	window.location.href='Signup.jsp';
}
</script>
</head>
<body>
<%@ include file="/Master.jsp" %>

    <form action="Login" method="post">
    <div>
        <table align="center" border="0" style="margin-top:200px;border-color:Black;border: 1px solid black;"  >
            <tr>
                <td colspan="3" align="center">
                    <b>Login</b>
                </td>
            </tr>
            <tr>
                <td>
                    UserName
                </td>
                <td>
                    :
                </td>
                <td>
                    <input type="text" name="txtUsername" value='<%out.print(request.getParameter("txtUsername")!=null?request.getParameter("txtUsername"):"");%>'/>
                </td>
            </tr>
            <tr>
                <td colspan="3"></td>
            </tr>
            <tr>
                <td>
                    Password
                </td>
                <td>
                    :
                </td>
                <td>
                    <input type="password" name="txtPassword" />
                </td>
            </tr>
            <tr>
                <td colspan="3"></td>
            </tr>
            <tr>
                <td colspan="3">
                <input type="checkbox" name="chkAdmin" value="isAdmin">&nbsp;&nbsp; Admin User 
                </td>
            </tr>
            <tr>
            
                <td colspan="3"><h3> <label id="errorMessage"><% out.print(request.getAttribute("errorMsg")!=null?request.getAttribute("errorMsg"):"");%> </label></h3> </td>
            </tr>
            <tr>
                <td colspan="3" width="100%">
                <table width="100%"> <tr><td width="50%"> <input type="submit" value="Login" /> </td> <td><input type="button" onclick="openSignup()" value="Sign Up" /></td> </tr></table>
                   </td>
            </tr>
        </table>
    </div>
    </form>
</body>
</html>