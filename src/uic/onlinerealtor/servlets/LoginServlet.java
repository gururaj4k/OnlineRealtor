package uic.onlinerealtor.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uic.onlinerealtor.business.RealtorManager;
import uic.onlinerealtor.entities.Question;
import uic.onlinerealtor.entities.UserDetails;
import uic.onlinerealtor.entities.UserPreference;
import uic.onlinerealtor.entities.UserPreference.UserType;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Got the Request in get....");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub
		System.out.println("Got the Reques....");
		String username = request.getParameter("txtUsername");
		String password = request.getParameter("txtPassword");
		
		String isAdmin=request.getParameter("chkAdmin");
		RealtorManager realtorManager = new RealtorManager();
		UserDetails userDet = new UserDetails();
		userDet.setUsername(username);
		userDet.setPassword(password);
		if (isAdmin!=null && isAdmin.equals("isAdmin")){
			userDet.setUsertype(UserType.Admin);
		}
		userDet = realtorManager.authenticateUser(userDet);
		if (userDet != null) {
			System.out.println("Success....");
			// Create the Session with User login Details.
			HttpSession session = request.getSession(true); // default parameter
			// update the session with usercontext.
			// This variable needs to be checked for user login status.
			session.setAttribute("userContext", userDet);
			if (!userDet.getUsertype().equals("Admin"))
				response.sendRedirect("Home.jsp");
			else
				response.sendRedirect("AdminView.jsp");
		} else {
			System.out.println("Failed....");
			request.setAttribute("errorMsg",
					"Login Failed.Please Check Your Credentials.");
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}

	}

}
