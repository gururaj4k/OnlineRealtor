package uic.onlinerealtor.entities;

public class UserPreference {
	private UserType userProfession;
	private String companyName;
	private String preferredLocation;
	private String childrenAge;
	private String havingChildren;
	private SubUrbOrDownTown subUrbOrDownTown;
	private String houseType;
	private int noOfStoreys;
	private String maritalStatus;
	private int pplStayingAtHome;
	private int noOfBedrooms;
	private double incomeRange;
	private boolean isEarning;
	private int noOfpplStaying;
	private Boolean pets;

	/**
	 * @return Name of the company
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the name of the company
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	

	/**
	 * @return Whether pets are allowed
	 */
	public boolean getpets() {
		return pets;
	}

	/**
	 * @param pets Whether pets are allowed
	 */
	public void setPets(boolean pets) {
		this.pets = pets;
	}

	/**
	 * @return whether there are Children
	 */
	public String getHavingChildren() {
		return havingChildren;
	}

	/**
	 * @param havingChildren whether there are Children
	 */
	public void setHavingChildren(String havingChildren) {
		this.havingChildren = havingChildren;
	}

	/**
	 * @return the age range of the Children
	 */
	public String getChildrenAge() {
		return childrenAge;
	}

	/**
	 * @param childrenAge The age Range of Children
	 */
	public void setChildrenAge(String childrenAge) {
		this.childrenAge = childrenAge;
	}

	/**
	 * @return The number of Storeys
	 */
	public int getNoOfStoreys() {
		return noOfStoreys;
	}

	/**
	 * @param noOfStoreys The number of Storeys
	 */
	public void setNoOfStoreys(int noOfStoreys) {
		this.noOfStoreys = noOfStoreys;
	}

	/**
	 * @return Whether Suburb or Downtown
	 */
	public SubUrbOrDownTown getSubUrbOrDownTown() {
		return subUrbOrDownTown;
	}

	/**
	 * @param subUrbOrDownTown Whether Suburb or DownTown
	 */
	public void setSubUrbOrDownTown(SubUrbOrDownTown subUrbOrDownTown) {
		this.subUrbOrDownTown = subUrbOrDownTown;
	}

	/**
	 * @return whether Apartment or Independent house
	 */
	public String getHouseType() {
		return houseType;
	}

	/**
	 * @param houseType whether Apartment or Independent house
	 */
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	/**
	 * @return whether he is Earning or not
	 */
	public boolean getIsEarning() {
		return isEarning;
	}

	/**
	 * @param isEarning whether he is Earning or not
	 */
	public void setIsEarning(boolean isEarning) {
		this.isEarning = isEarning;
	}

	/**
	 * @return The number of people staying
	 */
	public int getNoOfpplStaying() {
		return noOfpplStaying;
	}

	/**
	 * @param noOfpplStaying The number of people Staying
	 */
	public void setNoOfpplStaying(int noOfpplStaying) {
		this.noOfpplStaying = noOfpplStaying;
	}

	/**
	 * @return the income Range
	 */ 
	public double getIncomeRange() {
		return incomeRange;
	}

	/**
	 * @param incomeRange The Income RAnge
	 */
	public void setIncomeRange(double incomeRange) {
		this.incomeRange = incomeRange;
	}

	/**
	 * @return the Number of BedRooms
	 */
	public int getNoOfBedrooms() {
		return noOfBedrooms;
	}

	/**
	 * @param noOfBedrooms the number of bedrooms
	 */
	public void setNoOfBedrooms(int noOfBedrooms) {
		this.noOfBedrooms = noOfBedrooms;
	}

	/**
	 * @return the profession of the user
	 */
	public UserType getUserProfession() {
		return userProfession;
	}

	/**
	 * @param usertype the profession of the user
	 */
	public void setUserProfession(UserType usertype) {
		this.userProfession = usertype;
	}

	/**
	 * @return the location
	 */
	public String getPreferredLocation() {
		return preferredLocation;
	}

	/**
	 * @param preferredLocation the name of the location
	 */
	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}

	/**
	 * @return whether married or single
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus whether married or single
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return Number of people 
	 */
	public int getPplStayingAtHome() {
		return pplStayingAtHome;
	}

	/**
	 * @param pplStayingAtHome The Number of people
	 */
	public void setPplStayingAtHome(int pplStayingAtHome) {
		this.pplStayingAtHome = pplStayingAtHome;
	}

	/**
	 * @author Navya type of the user
	 *
	 */
	public enum UserType {
		Student, Employee, Admin
	}

	/**
	 * @author Navya Suburb or Downtown
	 *
	 */
	public enum SubUrbOrDownTown {
		Suburb, Downtown
	}
}
