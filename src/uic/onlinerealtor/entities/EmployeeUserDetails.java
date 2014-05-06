package uic.onlinerealtor.entities;

public class EmployeeUserDetails extends UserDetails {
private String employerName;
private String street;
private String doorNo;
private String zipcode;
private int cityId;

/**
 * @return The name of the employee 
 */
public String getEmployer() {
	return employerName;
}
/**
 * @param employerName Name of the employee
 */
public void setEmployer(String employerName) {
	this.employerName = employerName;
}
/**
 * @return The Zip code 
 */
public String getZipcode() {
	return zipcode;
}
/**
 * @param zipcode zip code of the employee
 */
public void setZipcode(String zipcode) {
	this.zipcode = zipcode;
}
/**
 * @return the unique id of the city
 */
public int getCityId() {
	return cityId;
}
/**
 * @param cityId unique id of the city
 */
public void setCityId(int cityId) {
	this.cityId = cityId;
}
/**
 * @return name of the Street
 */
public String getStreet() {
	return street;
}
/**
 * @param street Name of the street
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
