package uic.onlinerealtor.servlets;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uic.onlinerealtor.business.RealtorManager;
import uic.onlinerealtor.entities.Assets;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
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
		try {
			String addressline1 = request.getParameter("addressline1");
			String addressline2 = request.getParameter("addressline2");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String amenities = request.getParameter("amenities");
			String ownername = request.getParameter("ownername");
			String typesofrooms = request.getParameter("typesofrooms");
			String floortype = request.getParameter("floortype");
			String typeofroof = request.getParameter("typeofroof");
			String fullyfurnished = request.getParameter("fullyfurnished");
			String clubmembership = request.getParameter("clubmembership");
			String ownercontact = request.getParameter("ownercontact")
					.toString();
			String price = request.getParameter("price").toString();
			String latitude = request.getParameter("latitude").toString();
			String longitude = request.getParameter("longitude").toString();
			String noofbedrooms = request.getParameter("noofbedrooms")
					.toString();
			String ageofhome = request.getParameter("ageofhome").toString();
			String Distfromdowntown = request.getParameter("Distfromdowntown")
					.toString();
			String section = request.getParameter("section").toString();
			Assets asset = new Assets();
			asset.setAddressline1(addressline1);
			asset.setAddressline2(addressline2);
			asset.setCity(city);
			asset.setState(state);
			asset.setAssetKey(UUID.randomUUID().toString());
			asset.setAmenities(amenities);
			asset.setOwnername(ownername);
			asset.setTypesofrooms(typesofrooms);
			asset.setFloortype(floortype);
			asset.setTypeofroof(typeofroof);
			asset.setFullyfurnished(fullyfurnished);
			asset.setClubmembership(clubmembership);
			asset.setOwnercontact(ownercontact);
			asset.setPrice(Double.valueOf(price));
			asset.setNoofbedrooms(Integer.valueOf(noofbedrooms));
			asset.setAgeofhome(Integer.valueOf(ageofhome));
			asset.setDistfromdowntown(Integer.valueOf(Distfromdowntown));
			asset.setSection(Integer.valueOf(section));
			asset.setLatitude(Double.valueOf(latitude));
			asset.setLongitude(Double.valueOf(longitude));
			RealtorManager r = new RealtorManager();
			r.saveNewAsset(asset);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());			
		}
	}

}
