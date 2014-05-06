package uic.onlinerealtor.entities;

public class StudentUserDetails extends UserDetails {
	private String univName;
	private String street;
	private String doorNo;
	private String zipcode;
	private int cityId;
	
	/**
	 * @return The name of the university
	 */
	public String getUnivName() {
		return univName;
	}
	/**
	 * @param univName The name of the University
	 */
	public void setUnivName(String univName) {
		this.univName = univName;
	}
	
	/**
	 * @return The zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}
	/**
	 * @param zipcode sets the Zip code
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	/**
	 * @return The unique ID of the city
	 */
	public int getCityId() {
		return cityId;
	}
	/**
	 * @param cityId The unique ID of the city
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	/**
	 * @return THe name of the Street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street The name of the Street
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @return The door number
	 */
	public String getDoorNo() {
		return doorNo;
	}
	/**
	 * @param doorNo The door Number
	 */
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	
	

}
