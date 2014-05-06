package uic.onlinerealtor.entities;

public class City {
	private String cityname;
	private int cityId;
	/**
	 * @return the name of the city  
	 */
	public String getCityname() {
		return cityname;
	}
	/**
	 * @param cityname name of the city
	 */
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	/**
	 * @return the unique id for the city
	 */
	public int getCityId() {
		return cityId;
	}
	/**
	 * @param cityId unique id for the city
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

}
