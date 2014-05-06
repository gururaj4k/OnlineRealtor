package uic.onlinerealtor.entities;

import uic.onlinerealtor.entities.UserPreference.UserType;

public class UserDetails {
private String username;
private String password;
private String firstname;
private String lastname;
private String email;
private String phonenum;
private String gender;
private UserType usertype;
private int userId;

/**
 * @return the unique id of the user
 */
public int getUserId() {
	return userId;
}
/**
 * @param userId the unique id of the user
 */
public void setUserId(int userId) {
	this.userId = userId;
}
/**
 * @return the username
 */
public String getUsername() {
	return username;
}
/**
 * @param username the username
 */
public void setUsername(String username) {
	this.username = username;
}
/**
 * @return the password of the user
 */
public String getPassword() {
	return password;
}
/**
 * @param password the name of the password
 */
public void setPassword(String password) {
	this.password = password;
}
/**
 * @return the first name of the user
 */
public String getFirstname() {
	return firstname;
}
/**
 * @param firstname the first name of the user
 */
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
/**
 * @return the last name of the user
 */
public String getLastname() {
	return lastname;
}
/**
 * @param lastname the last name of the user
 */
public void setLastname(String lastname) {
	this.lastname = lastname;
}
/**
 * @return the Email of the user
 */
public String getEmail() {
	return email;
}
/**
 * @param email The Email of the user
 */
public void setEmail(String email) {
	this.email = email;
}
/**
 * @return the phone number of the user
 */

public String getPhonenum() {
	return phonenum;
}
/**
 * @param phonenum the phone number of the user
 */
public void setPhonenum(String phonenum) {
	this.phonenum = phonenum;
}
/**
 * @return the user gender
 */
public String getGender() {
	return gender;
}
/**
 * @param gender The gender of the user
 */
public void setGender(String gender) {
	this.gender = gender;
}
/**
 * @return The type of the user
 */
public UserType getUsertype() {
	return usertype;
}
/**
 * @param usertype the type of the user
 */
public void setUsertype(UserType usertype) {
	this.usertype = usertype;
}



}
