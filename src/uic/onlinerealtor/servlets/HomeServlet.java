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
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		loadFirstQuestion(request, response);
		RequestDispatcher rd=request.getRequestDispatcher("QuestionsMain.jsp");
		rd.forward(request, response);
	}
	
	private void loadFirstQuestion(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("currentQuestion") == null) {
			Question q = new Question();
			UserDetails userDet = (UserDetails) session
					.getAttribute("userContext");
			q.setQuestionId(0);
			q.setUserAns(userDet.getUsertype().toString());
			RealtorManager realtorManager = new RealtorManager();
			Question question = realtorManager.getNextQuestion(q);
			UserPreference userPref = new UserPreference();
			if (userDet.getUsertype().equals("Student"))
				userPref.setUserProfession(UserType.Student);
			else
				userPref.setUserProfession(UserType.Employee);
			session.setAttribute("UserPreference",userPref);			
			System.out.println("I am in Login Servlet.. :"+question.getQuestionId());
			session.setAttribute("currentQuestion", question);
		}

	}

}
