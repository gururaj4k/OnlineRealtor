package uic.onlinerealtor.entities;

import java.util.List;

public class UserAnalysis {

	private float baseMultiplicationFactor;
	private List<String> suggestedAmenities;
	private int preferrableBrCount;
	private String location;
	private String hasGarage;
	private String hasGym;
	private String typeFloor;
	private String hasBasement;	
	private double minPriceRange;
	private double maxPriceRance=2000;
	private int noOfParkinglots;
	private int numberOfSprinkler;
	private boolean isNewHome;
	private boolean isFirePlaceAvailable;
	private boolean isSportsRoomAvailable;
	private boolean isCentralizedCoolingAvailable; 
	private boolean isRecreationRoomAvailable; 
	private boolean isDishWasherAvailable;
	private boolean isCentralizedHeating;
	private boolean isLaundaryAvailable;
	private boolean isbalconyAvailable;
	private boolean isSwimmingPoolAvailable;
	private String hasClubMembership;
	private boolean hasCentralizedHeating;
	private boolean isFamilyRoomAvailable;
	private boolean isGuestRoomAvailable;
	private boolean isDownstairsRoomAvailable;
	private boolean isOfficeRoomAvailable;
	private boolean isMastersBedRoomAvailable;
	private boolean isFurnished;
	
	/**
	 * @return the House is Furnished or not
	 */
	public boolean isFurnished() {
		return isFurnished;
	}

	/**
	 * @param isFurnished The house is Furnished or not
	 */
	public void setFurnished(boolean isFurnished) {
		this.isFurnished = isFurnished;
	}
	/**
	 * @return The type of the flooring
	 */
	public String getTypeFloor() {
		return typeFloor;
	}

	/**
	 * @param typeFloor The type of the flooring
	 */
	public void setTypeFloor(String typeFloor) {
		this.typeFloor = typeFloor;
	}

	/**
	 * @return Whether the property has basement
	 */
	public String getHasBasement() {
		return hasBasement;
	}

	/**
	 * @param hasBasement Whether the property has basement
	 */
	public void setHasBasement(String hasBasement) {
		this.hasBasement = hasBasement;
	}
	/**
	 * @return Whether the property has an office room 
	 */
	public boolean isOfficeRoomAvailable() {
		return isOfficeRoomAvailable;
	}

	/**
	 * @param isOfficeRoomAvailable Whether the property has an office room
	 */
	public void setOfficeRoomAvailable(boolean isOfficeRoomAvailable) {
		this.isOfficeRoomAvailable = isOfficeRoomAvailable;
	}
	/**
	 * @return whether the property has Master room
	 */
	public boolean isMastersBedRoomAvailable() {
		return isMastersBedRoomAvailable;
	}

	/**
	 * @param isMastersBedRoomAvailable whether the property has Master room
	 */
	public void setMastersBedRoomAvailable(boolean isMastersBedRoomAvailable) {
		this.isMastersBedRoomAvailable = isMastersBedRoomAvailable;
	}
	/**
	 * @return whether the property has store room
	 */
	public boolean isDownstairsRoomAvailable() {
		return isDownstairsRoomAvailable;
	}

	/**
	 * @param isDownstairsRoomAvailable whether the property has store room
	 */
	public void setDownstairsRoomAvailable(boolean isDownstairsRoomAvailable) {
		this.isDownstairsRoomAvailable = isDownstairsRoomAvailable;
	}
	/**
	 * @return Whether the property has guest room
	 */
	public boolean isGuestRoomAvailable() {
		return isGuestRoomAvailable;
	}

	/**
	 * @param isGuestRoomAvailable Whether the property has guest room
	 */
	public void setGuestRoomAvailable(boolean isGuestRoomAvailable) {
		this.isGuestRoomAvailable = isGuestRoomAvailable;
	}

	/**
	 * @return Whether the property has Family room
	 */
	public boolean isFamilyRoomAvailable() {
		return isFamilyRoomAvailable;
	}

	/**
	 * @param isFamilyRoomAvailable Whether the Property has Family room
	 */
	public void setFamilyRoomAvailable(boolean isFamilyRoomAvailable) {
		this.isFamilyRoomAvailable = isFamilyRoomAvailable;
	}

	/**
	 * @return Whether the Property has a club membership
	 */
	public String getHasClubMembership() {
		return hasClubMembership;
	}

	/**
	 * @param hasClubMembership Whether the Property has club membership
	 */
	public void setHasClubMembership(String hasClubMembership) {
		this.hasClubMembership = hasClubMembership;
	}
	
	/**
	 * @return whether the property has a Sports room
	 */
	public boolean isSportsRoomAvailable() {
		return isSportsRoomAvailable;
	}

	/**
	 * @param isSportsRoomAvailable Whether the property has sports room
	 */
	public void setSportsRoomAvailable(boolean isSportsRoomAvailable) {
		this.isSportsRoomAvailable = isSportsRoomAvailable;
	}

	/**
	 * @return The number of SPrinklers in the garden
	 */
	public int getNumberOfSprinkler() {
		return numberOfSprinkler;
	}

	/**
	 * @param numberOfSprinkler The number of Sprinklers in the garden
	 */
	public void setNumberOfSprinkler(int numberOfSprinkler) {
		this.numberOfSprinkler = numberOfSprinkler;
	}
	/**
	 * @return Whether the property has a recreation room
	 */
	public boolean isRecreationRoomAvailable() {
		return isRecreationRoomAvailable;
	}

	/**
	 * @param isRecreationRoomAvailable Whether the Property has a Recreation room
	 */
	public void setRecreationRoomAvailable(boolean isRecreationRoomAvailable) {
		this.isRecreationRoomAvailable = isRecreationRoomAvailable;
	}
	/**
	 * @return Whether the property has LAundry Facility
	 */
	public boolean isLaundaryAvailable() {
		return isLaundaryAvailable;
	}

	/**
	 * @param isLaundaryAvailable Whether the Property has a Laundry facility
	 */
	public void setLaundaryAvailable(boolean isLaundaryAvailable) {
		this.isLaundaryAvailable = isLaundaryAvailable;
	}

	
	
	/**
	 * @return Whether the property has a balcony
	 */
	public boolean isIsbalconyAvailable() {
		return isbalconyAvailable;
	}

	/**
	 * @return Whether the Property has a swimming pool
	 */
	public boolean isSwimmingPoolAvailable() {
		return isSwimmingPoolAvailable;
	}

	/**
	 * @param isSwimmingPoolAvailable Whether the property has a swimming pool 
	 */
	public void setSwimmingPoolAvailable(boolean isSwimmingPoolAvailable) {
		this.isSwimmingPoolAvailable = isSwimmingPoolAvailable;
	}
	
	/**
	 * @param isbalconyAvailable Whether the property has a balcony
	 */
	public void setIsbalconyAvailable(boolean isbalconyAvailable) {
		this.isbalconyAvailable = isbalconyAvailable;
	}

	/**
	 * @return Whether the property has a gym
	 */
	public String getHasGym() {
		return hasGym;
	}

	/**
	 * @param hasGym Whether the Propert has a gym
	 */
	public void setHasGym(String hasGym) {
		this.hasGym = hasGym;
	}
	/**
	 * @return Whether Property has centralized cooling
	 */
	public boolean isCentralizedCoolingAvailable() {
		return isCentralizedCoolingAvailable;
	}

	/**
	 * @param isCentralizedCoolingAvailable whether the property has centralized cooling
	 */
	public void setCentralizedCoolingAvailable(boolean isCentralizedCoolingAvailable) {
		this.isCentralizedCoolingAvailable = isCentralizedCoolingAvailable;
	}

	/**
	 * @return the property has fire place
	 */
	public boolean isFirePlaceAvailable() {
		return isFirePlaceAvailable;
	}

	/**
	 * @param isFirePlaceAvailable whether the property has a fire place
	 */
	public void setFirePlaceAvailable(boolean isFirePlaceAvailable) {
		this.isFirePlaceAvailable = isFirePlaceAvailable;
	}

	
	/**
	 * @return Whether the property has a dishwasher
	 */
	public boolean isDishWasherAvailable() {
		return isDishWasherAvailable;
	}

	/**
	 * @param isDishWasherAvailable Whether the propert y has a dish washer
	 */
	public void setDishWasherAvailable(boolean isDishWasherAvailable) {
		this.isDishWasherAvailable = isDishWasherAvailable;
	}


	/**
	 * @return whether the property has a centralized heating
	 */
	public boolean isHasCentralizedHeating() {
		return hasCentralizedHeating;
	}

	/**
	 * @param hasCentralizedHeating Whether the property has a centraizd heating
	 */
	public void setHasCentralizedHeating(boolean hasCentralizedHeating) {
		this.hasCentralizedHeating = hasCentralizedHeating;
	}



	/**
	 * @return Whether the property has a garage
	 */
	public String getHasGarage() {
		return hasGarage;
	}

	/**
	 * @param hasGarage Whether teh property has a garage
	 */
	public void setHasGarage(String hasGarage) {
		this.hasGarage = hasGarage;
	}
	/**
	 * @return whether the home is built recently
	 */
	public boolean isNewHome() {
		return isNewHome;
	}

	/**
	 * @param isNewHome Whether the property is newly built
	 */
	public void setNewHome(boolean isNewHome) {
		this.isNewHome = isNewHome;
	}

	/**
	 * @return whether the property has a centralised Heating
	 */
	public boolean isCentralizedHeating() {
		return isCentralizedHeating;
	}

	/**
	 * @param isCentralizedHeating Whether the property has centralized heating
	 */
	public void setCentralizedHeating(boolean isCentralizedHeating) {
		this.isCentralizedHeating = isCentralizedHeating;
	}

	/**
	 * @return the number of parking lots
	 */
	public int getNoOfParkinglots() {
		return noOfParkinglots;
	}

	/**
	 * @param noOfParkinglots the number of parking lots
	 */
	public void setNoOfParkinglots(int noOfParkinglots) {
		this.noOfParkinglots = noOfParkinglots;
	}

	/**
	 * @return the minimum price of property
	 */
	public double getMinPriceRsange() {
		return minPriceRange;
	}

	/**
	 * @param minPriceRange Minimum price of the property
	 */
	public void setMinPriceRange(double minPriceRange) {
		this.minPriceRange = minPriceRange;
	}

	/**
	 * @return The maximum price of the property
	 */
	public double getMaxPriceRance() {
		return maxPriceRance;
	}

	/**
	 * @param maxPriceRance The maximum price of the property
	 */
	public void setMaxPriceRance(double maxPriceRance) {
		this.maxPriceRance = maxPriceRance;
	}

	/**
	 * @return the location 
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location The location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the base multiplication factor
	 */
	public float getBaseMultiplicationFactor() {
		return baseMultiplicationFactor;
	}

	/**
	 * @param baseMultiplicationFactor the base Multiplication factor
	 */
	public void setBaseMultiplicationFactor(float baseMultiplicationFactor) {
		this.baseMultiplicationFactor = baseMultiplicationFactor;
	}

	/**
	 * @return the amenities of the property
	 */
	public List<String> getSuggestedAmenities() {
		return suggestedAmenities;
	}

	/**
	 * @param suggestedAmenities The amenities of the property
	 */
	public void setSuggestedAmenities(List<String> suggestedAmenities) {
		if (this.suggestedAmenities != null) {
			this.suggestedAmenities.addAll(suggestedAmenities);
		} else
			this.suggestedAmenities = suggestedAmenities;
	}

	/**
	 * @return The Number of bedrooms
	 */
	public int getPreferrableBrCount() {
		return preferrableBrCount;
	}

	/**
	 * @param preferrableBrCount the number of bedrooms
	 */
	public void setPreferrableBrCount(int preferrableBrCount) {
		this.preferrableBrCount = preferrableBrCount;
	}
}
