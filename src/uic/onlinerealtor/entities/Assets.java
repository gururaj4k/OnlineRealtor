package uic.onlinerealtor.entities;

public class Assets {
	private String addressline1;
	private String addressline2;
	private String city;
	private String state;
	private String amenities;
	private String ownername;
	private String typesofrooms;
	private String floortype;
	private String typeofroof;
	private String fullyfurnished;
	private String clubmembership;
	private String ownercontact;
	private double estgasbill;
	private double price;
	private int noofbedrooms;	
	private int ageofhome;	
	private int distfromdowntown;
	private int section;
	private double latitude;
	private double longitude;
	private String assetKey;
	
	/**
	 * @return the value of assetkey
	 */
	public String getAssetKey() {
		return assetKey;
	}
	/**
	 * @param assetKey value of assetKey 
	 */
	public void setAssetKey(String assetKey) {
		this.assetKey = assetKey;
	}
	/**
	 * @return the address line
	 */
	public String getAddressline1() {
		return addressline1;
	}
	/**
	 * @param addressline1 address of the property
	 */
	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}
	/**
	 * @return address of the property
	 */
	public String getAddressline2() {
		return addressline2;
	}
	/**
	 * @param addressline2 address of the property
	 */
	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}
	/**
	 * @return the city of the property
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city city name
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state of the property
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state state name
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the amenities of the property
	 */
	public String getAmenities() {
		return amenities;
	}
	/**
	 * @param amenities of the property
	 */
	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}
	/**
	 * @return name of the owner of the property 
	 */
	public String getOwnername() {
		return ownername;
	}
	/**
	 * @param ownername name of the property owner
	 */
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	/**
	 * @return types of room in the property
	 */
	public String getTypesofrooms() {
		return typesofrooms;
	}
	/**
	 * @param typesofrooms in the property
	 */
	public void setTypesofrooms(String typesofrooms) {
		this.typesofrooms = typesofrooms;
	}
	/**
	 * @return the floor type of the property
	 */
	public String getFloortype() {
		return floortype;
	}
	/**
	 * @param floortype of the property
	 */
	public void setFloortype(String floortype) {
		this.floortype = floortype;
	}
	/**
	 * @return the type of roof of the property
	 */
	public String getTypeofroof() {
		return typeofroof;
	}
	/**
	 * @param typeofroof roof type of the property
	 */
	public void setTypeofroof(String typeofroof) {
		this.typeofroof = typeofroof;
	}
	/**
	 * @return whether the property is furnished 
	 */
	public String getFullyfurnished() {
		return fullyfurnished;
	}
	/**
	 * @param fullyfurnished sets  whether the property is furnished
	 */
	public void setFullyfurnished(String fullyfurnished) {
		this.fullyfurnished = fullyfurnished;
	}
	/**
	 * @return whether the property has a membership to any club
	 */
	public String getClubmembership() {
		return clubmembership;
	}
	/**
	 * @param clubmembership sets whether the property has a membership to any club
	 */
	public void setClubmembership(String clubmembership) {
		this.clubmembership = clubmembership;
	}
	/**
	 * @return the contact of the property of owner
	 */
	public String getOwnercontact() {
		return ownercontact;
	}
	/**
	 * @param owner contact number the contact of the property owner
	 */
	public void setOwnercontact(String ownercontact) {
		this.ownercontact = ownercontact;
	}
	
	/**
	 * @return the amount of gas bill
	 */
	public double getEstgasbill() {
		return estgasbill;
	}
	/**
	 * @param estgasbill amount of gass bill generated for that property in general
	 */
	public void setEstgasbill(double estgasbill) {
		this.estgasbill = estgasbill;
	}
	/**
	 * @return the cost of the property
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price cost of the property
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the number of bedrooms in the property
	 */
	public int getNoofbedrooms() {
		return noofbedrooms;
	}
	/**
	 * @param noofbedrooms number of bedrooms in the property
	 */
	public void setNoofbedrooms(int noofbedrooms) {
		this.noofbedrooms = noofbedrooms;
	}
	/**
	 * @return how old the property is
	 */
	public int getAgeofhome() {
		return ageofhome;
	}
	/**
	 * @param ageofhome age of the property
	 */
	public void setAgeofhome(int ageofhome) {
		this.ageofhome = ageofhome;
	}
	/**
	 * @return the distance in miles from downtown to property
	 */
	public int getDistfromdowntown() {
		return distfromdowntown;
	}
	/**
	 * @param distfromdowntown  Distance in miles from downtown to the property
	 */
	public void setDistfromdowntown(int distfromdowntown) {
		this.distfromdowntown = distfromdowntown;
	}
	/**
	 * @return Section of the property
	 */
	public int getSection() {
		return section;
	}
	/**
	 * @param section section of the property
	 */
	public void setSection(int section) {
		this.section = section;
	}
	/**
	 * @return the latitude of the property
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude sets of the property
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude of the property
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude of the Property
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
	
