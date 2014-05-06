package uic.onlinerealtor.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uic.onlinerealtor.business.RealtorManager;
import uic.onlinerealtor.entities.EmployeeUserDetails;
import uic.onlinerealtor.entities.StudentUserDetails;
import uic.onlinerealtor.entities.UserPreference.UserType;


/**
 * Servlet implementation class SignupServlet
 */
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		boolean resp=false;
		String username=request.getParameter("username");
		String password= request.getParameter("password");
		String email=request.getParameter("email");
		String fname = request.getParameter("Fname");
		String lname = request.getParameter("LName");
		String userType=request.getParameter("usertype");
		String phonenum = request.getParameter("Phone");
		String gender = request.getParameter("gender");
		//UserDetails userDetails = new UserDetails();
		RealtorManager realtorManager = new RealtorManager();
		if(userType.equals("Student"))
		{
			StudentUserDetails studentUserDetails = new StudentUserDetails();
			studentUserDetails.setUsername(username);
			studentUserDetails.setPassword(password);
			studentUserDetails.setFirstname(fname);
			studentUserDetails.setLastname(lname);
			studentUserDetails.setEmail(email);
			studentUserDetails.setPhonenum(phonenum);
			studentUserDetails.setGender(gender);
			studentUserDetails.setUsertype(UserType.Student);
			studentUserDetails.setUnivName(request.getParameter("txtStUniv"));
			studentUserDetails.setDoorNo(request.getParameter("txtHouseNo"));
			studentUserDetails.setStreet(request.getParameter("txtStreet"));
			studentUserDetails.setZipcode(request.getParameter("txtZipCode"));
			studentUserDetails.setCityId(Integer.valueOf(request.getParameter("ddlCity")));
			resp=realtorManager.signupUser(studentUserDetails);
		}
		else if(userType.equals("Employee"))
		{
			EmployeeUserDetails employeeUserDetails = new EmployeeUserDetails();
			employeeUserDetails.setUsername(username);
			employeeUserDetails.setPassword(password);
			employeeUserDetails.setFirstname(fname);
			employeeUserDetails.setLastname(lname);
			employeeUserDetails.setEmail(email);
			employeeUserDetails.setPhonenum(phonenum);
			employeeUserDetails.setGender(gender);
			employeeUserDetails.setUsertype(UserType.Employee);			
			employeeUserDetails.setEmployer(request.getParameter("txtEmployer"));			
			employeeUserDetails.setDoorNo(request.getParameter("txtHouseNo"));
			employeeUserDetails.setStreet(request.getParameter("txtStreet"));
			employeeUserDetails.setZipcode(request.getParameter("txtZipCode"));
			System.out.println("City Selected :"+request.getParameter("ddlCity"));
			employeeUserDetails.setCityId(Integer.valueOf(request.getParameter("ddlCity")));
			resp=realtorManager.signupUser(employeeUserDetails);
		}		
		RequestDispatcher rd;
		if(resp)
			response.sendRedirect("Login.jsp");
		else{
			System.out.println("username exists errpr");
			request.setAttribute("errormessage", "UserName already Exists. Please try with a different one.");
			rd=request.getRequestDispatcher("Signup.jsp");
			rd.forward(request, response);
		}
	}

}
