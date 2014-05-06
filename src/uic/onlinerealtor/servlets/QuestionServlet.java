package uic.onlinerealtor.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uic.onlinerealtor.business.RealtorManager;
import uic.onlinerealtor.entities.Assets;
import uic.onlinerealtor.entities.Question;
import uic.onlinerealtor.entities.UserPreference;
import uic.onlinerealtor.entities.UserPreference.SubUrbOrDownTown;
import uic.onlinerealtor.entities.UserPreference.UserType;

/**
 * Servlet implementation class QuestionServlet
 */
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuestionServlet() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("This is post method");
		String hdnvalue = request.getParameter("hdnQuesID");
		System.out.println("hidden value :" + hdnvalue);
		int questionId = Integer.valueOf(request.getParameter("hdnQuesID"));
		// Update questionid in Session
		HttpSession session = request.getSession();
		if (session.getAttribute("currentQuestion") != null) {
			Question q = (Question) session.getAttribute("currentQuestion");
			q.setQuestionId(questionId);
		}
		if (questionId == 1) {
			System.out.println("isStudentEarning calling");
			isStudentEarning(request, response);
		} else if (questionId == 2) {
			System.out.println("userIncomeRange calling");
			userIncomeRange(request, response);
		} else if (questionId == 3) {
			System.out.println("userHouseType calling");
			userHouseType(request, response);
		} else if (questionId == 4)
			userStorey(request, response);
		else if (questionId == 5)
			userMarritalStatus(request, response);
		else if (questionId == 6)
			userChildren(request, response);
		else if (questionId == 7)
			userChildrenAge(request, response);
		else if (questionId == 8)
			userNoofpeople(request, response);
		else if (questionId == 9)
			employeeCompany(request, response);
		else {
			displayResults(request, response);
		}

	}

	private void isStudentEarning(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			UserPreference userPref = null;
			boolean isNextAvailable=false;
			System.out
					.println("this handles whether the student is earning or not");
			String isEarning = request.getParameter("isEarning");
			HttpSession session = request.getSession();
			if (session.getAttribute("UserPreference") != null)
				userPref = (UserPreference) session
						.getAttribute("UserPreference");
			else
				userPref = new UserPreference();
			if (isEarning.equals("YesEarning")) {
				userPref.setIsEarning(true);
				isNextAvailable = getNextQuestion(request, response,
						Boolean.toString(true));
			} else{
				userPref.setIsEarning(true);
				isNextAvailable = getNextQuestion(request, response,
						Boolean.toString(false));
			}
			session.setAttribute("UserPreference", userPref);			
			if (isNextAvailable) {
				RequestDispatcher rd = request
						.getRequestDispatcher("QuestionsMain.jsp");
				rd.forward(request, response);
			} else
				displayResults(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void userIncomeRange(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			UserPreference userPref = null;
			System.out.println("This is incomerange page");
			String incomeRange = request.getParameter("incomeRange");
			HttpSession session = request.getSession();
			System.out.println("Income Range :" + incomeRange);
			if (session.getAttribute("UserPreference") != null)
				userPref = (UserPreference) session
						.getAttribute("UserPreference");
			if (incomeRange.equals("low")) {
				userPref.setIncomeRange(5000);
			} else if (incomeRange.equals("medium")) {
				userPref.setIncomeRange(8000);

			} else if (incomeRange.equals("high")) {
				userPref.setIncomeRange(16000);
			}

			session.setAttribute("UserPreference", userPref);
			boolean isNextAvailable = getNextQuestion(request, response,
					String.valueOf(userPref.getIncomeRange()));
			if (isNextAvailable) {
				RequestDispatcher rd = request
						.getRequestDispatcher("QuestionsMain.jsp");
				rd.forward(request, response);
			} else
				displayResults(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void userHouseType(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			UserPreference userPref = null;
			System.out.println("This page is to find housetype");
			String preferredLocation = request
					.getParameter("preferredLocation");
			String subUrbOrDownTown = request.getParameter("subUrbOrDownTown");
			String houseType = request.getParameter("houseType");
			HttpSession session = request.getSession();
			System.out.println("Location :" + preferredLocation);
			System.out.println("Suburb/Downtown :" + subUrbOrDownTown);
			System.out.println("HouseType :" + houseType);
			if (session.getAttribute("UserPreference") != null)
				userPref = (UserPreference) session
						.getAttribute("UserPreference");
			userPref.setPreferredLocation(preferredLocation);
			if (subUrbOrDownTown.equals(SubUrbOrDownTown.Suburb.toString()))
				userPref.setSubUrbOrDownTown(SubUrbOrDownTown.Suburb);
			else
				userPref.setSubUrbOrDownTown(SubUrbOrDownTown.Downtown);
			if (houseType.equals("Apartment"))
				userPref.setHouseType("Apartment");
			else
				userPref.setHouseType("IndependentHouse");

			session.setAttribute("UserPreference", userPref);
			boolean isNextAvailable = getNextQuestion(request, response,
					userPref.getHouseType());
			if (isNextAvailable) {
				RequestDispatcher rd = request
						.getRequestDispatcher("QuestionsMain.jsp");
				rd.forward(request, response);
			} else
				displayResults(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void userStorey(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			UserPreference userPref = null;
			System.out.println("This is No of Storeys Page");
			String storeys = request.getParameter("storeys");
			HttpSession session = request.getSession();
			System.out.println("No of Storeys :" + storeys);
			if (session.getAttribute("UserPreference") != null)
				userPref = (UserPreference) session
						.getAttribute("UserPreference");
			if (storeys.equals("1")) {
				userPref.setNoOfStoreys(1);

			} else if (storeys.equals("2")) {
				userPref.setNoOfStoreys(2);
			} else if (storeys.equals("3")) {
				userPref.setNoOfStoreys(3);
			}
			session.setAttribute("UserPreference", userPref);
			boolean isNextAvailable = getNextQuestion(request, response,
					String.valueOf(userPref.getNoOfStoreys()));
			if (isNextAvailable) {
				RequestDispatcher rd = request
						.getRequestDispatcher("QuestionsMain.jsp");
				rd.forward(request, response);
			} else
				displayResults(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void userMarritalStatus(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			UserPreference userPref = null;
			System.out.println("This page is to find Marrital Status");
			String bedRooms = request.getParameter("BedRooms");
			String marritalStatus = request.getParameter("MarritalStatus");
			HttpSession session = request.getSession();
			System.out.println("No Of Bedrooms :" + bedRooms);
			System.out.println("Suburb/Downtown :" + marritalStatus);
			if (session.getAttribute("UserPreference") != null)
				userPref = (UserPreference) session
						.getAttribute("UserPreference");
			if (bedRooms.equals("1")) {
				userPref.setNoOfBedrooms(1);
			} else if (bedRooms.equals("2")) {
				userPref.setNoOfBedrooms(2);
			} else if (bedRooms.equals("3")) {
				userPref.setNoOfBedrooms(3);
			}
			if (marritalStatus.equals("married"))
				userPref.setMaritalStatus(marritalStatus);
			else
				userPref.setMaritalStatus(marritalStatus);
			session.setAttribute("UserPreference", userPref);
			boolean isNextAvailable = getNextQuestion(request, response,
					userPref.getMaritalStatus());
			if (isNextAvailable) {
				RequestDispatcher rd = request
						.getRequestDispatcher("QuestionsMain.jsp");
				rd.forward(request, response);
			} else
				displayResults(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void userChildren(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			UserPreference userPref = null;
			System.out
					.println("this handles whether the student is earning or not");
			String havingChildren = request.getParameter("Children");
			HttpSession session = request.getSession();
			if (session.getAttribute("UserPreference") != null)
				userPref = (UserPreference) session
						.getAttribute("UserPreference");
			if (havingChildren.equals("yes")) {
				userPref.setHavingChildren(havingChildren);
			} else
				userPref.setHavingChildren(havingChildren);
			session.setAttribute("UserPreference", userPref);
			boolean isNextAvailable = getNextQuestion(request, response,
					userPref.getHavingChildren());
			if (isNextAvailable) {
				RequestDispatcher rd = request
						.getRequestDispatcher("QuestionsMain.jsp");
				rd.forward(request, response);
			} else
				displayResults(request, response);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void userChildrenAge(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			UserPreference userPref = null;
			System.out
					.println("this handles whether the student is earning or not");
			String childrenAge = request.getParameter("age");
			HttpSession session = request.getSession();
			if (session.getAttribute("UserPreference") != null)
				userPref = (UserPreference) session
						.getAttribute("UserPreference");
			if (childrenAge.equals("yes")) {
				userPref.setHavingChildren(childrenAge);
			} else
				userPref.setHavingChildren(childrenAge);
			session.setAttribute("UserPreference", userPref);
			boolean isNextAvailable = getNextQuestion(request, response,
					userPref.getHavingChildren());
			if (isNextAvailable) {
				RequestDispatcher rd = request
						.getRequestDispatcher("QuestionsMain.jsp");
				rd.forward(request, response);
			} else
				displayResults(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void userNoofpeople(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			UserPreference userPref = null;
			System.out
					.println("this handles whether the student is earning or not");
			String noOfPeople = request.getParameter("No of people");
			String pets = request.getParameter("pets");
			HttpSession session = request.getSession();
			if (session.getAttribute("UserPreference") != null)
				userPref = (UserPreference) session
						.getAttribute("UserPreference");
			userPref.setNoOfpplStaying(Integer.parseInt(noOfPeople));
			if (pets.equals("yes")) {
				userPref.setPets(true);
			} else
				userPref.setPets(false);
			session.setAttribute("UserPreference", userPref);
			boolean isNextAvailable = getNextQuestion(request, response,
					String.valueOf(userPref.getNoOfpplStaying()));
			if (isNextAvailable) {
				RequestDispatcher rd = request
						.getRequestDispatcher("QuestionsMain.jsp");
				rd.forward(request, response);
			} else
				displayResults(request, response);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void employeeCompany(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			UserPreference userPref = null;
			System.out
					.println("this handles which company employee is working");
			String companyName = request.getParameter("companyName");
			HttpSession session = request.getSession();
			if (session.getAttribute("UserPreference") != null)
				userPref = (UserPreference) session
						.getAttribute("UserPreference");
			else
				userPref = new UserPreference();
			userPref.setCompanyName(companyName);
			session.setAttribute("UserPreference", userPref);
			boolean isNextAvailable = getNextQuestion(request, response,
					userPref.getCompanyName());
			if (isNextAvailable) {
				RequestDispatcher rd = request
						.getRequestDispatcher("QuestionsMain.jsp");
				rd.forward(request, response);
			} else
				displayResults(request, response);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// connencts to drools gets the next question object,this is stored in the
	// session and later accessed in Questionsmain.jsp
	private boolean getNextQuestion(HttpServletRequest request,
			HttpServletResponse response, String userAns) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("currentQuestion") != null) {
			Question q = (Question) session.getAttribute("currentQuestion");
			q.setUserAns(userAns);
			RealtorManager realtorManager = new RealtorManager();
			System.out.println(q.getQuestionId() + " :" + q.getUserAns());
			Question question = realtorManager.getNextQuestion(q);
			if (question != null && question.getQuestion() != null) {				
				session.setAttribute("currentQuestion", question);
				return true;
			} else
				return false;

		}
		return false;
	}

	private void displayResults(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			System.out.println("This is display results");
			HttpSession session = request.getSession(true);
			UserPreference userPRef = (UserPreference) session
					.getAttribute("UserPreference");
			if (userPRef != null) {
				userPRef.setPplStayingAtHome(3);
				// userPRef.setPreferredLocation("Dallas");
				RealtorManager realtorMngr = new RealtorManager();
				List<Assets> assets = realtorMngr.searchAssets(userPRef);
				request.setAttribute("results1", assets);
				RequestDispatcher rd = request
						.getRequestDispatcher("Results.jsp");
				rd.forward(request, response);
			}
		} catch (Exception ex) {

		}
	}
}
