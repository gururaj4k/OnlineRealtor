package uic.onlinerealtor.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uic.onlinerealtor.business.RealtorManager;
import uic.onlinerealtor.entities.UserDetails;

/**
 * Servlet implementation class ResultsServlet
 */
public class ResultsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResultsServlet() {
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
		String[] assetKeys = request.getParameterValues("markFav");
		List<String> favouriteAssetsList = new ArrayList<String>();
		System.out.println("Marked Assets....");
		if (assetKeys != null) {
			for (String asset : assetKeys) {
				System.out.println(asset.lastIndexOf("\n"));
				asset = asset.replace("\n", "");
				if (asset.lastIndexOf("\n") != -1)
					System.out.println("New Line contains");
				System.out.print(asset);
				System.out.println(asset.lastIndexOf("\n"));
				favouriteAssetsList.add(asset);
			}
			RealtorManager realtorMngr = new RealtorManager();
			HttpSession session = request.getSession();
			if (session.getAttribute("userContext") != null) {
				UserDetails userDet = (UserDetails) session
						.getAttribute("userContext");
				boolean resp = realtorMngr.saveUserFavourties(
						userDet.getUserId(), favouriteAssetsList);
				RequestDispatcher rd = null;
				if (resp)
					rd = request.getRequestDispatcher("Favourties.jsp");
				else
					rd = request.getRequestDispatcher("Error.jsp");
				rd.forward(request, response);
			}
		}
	}

}
