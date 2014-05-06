package uic.onlinerealtor.dataaccess;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uic.onlinerealtor.dataaccess.DataPointerFactory.DataSource;
import uic.onlinerealtor.dataaccess.DataPointerFactory.Operators;
import uic.onlinerealtor.entities.Assets;
import uic.onlinerealtor.entities.City;
import uic.onlinerealtor.entities.EmployeeUserDetails;
import uic.onlinerealtor.entities.StudentUserDetails;
import uic.onlinerealtor.entities.UserAnalysis;
import uic.onlinerealtor.entities.UserDetails;
import uic.onlinerealtor.entities.UserPreference.UserType;
/**
 * This class contains the methods to interact with NoSql and Relational Database. It depends on the DataBaseFactory to
 * get the instance of required required(either  RealtorMongoDataSource or RealtorRelationalDataSource) in the form of 
 * IDataPointer interface.
 * All results obtained by the respective DataSources will be converted from JSONArray to Entity objects. 
 * @author OnlineRealtor
 * @version 1.0
 */
public class RealtorDAO {
	private IDataPointer datapointer;
	private JSONObject query;
	
	/** 
	 * Authenticate the user credentials against the Database	 * 
	 * @param user - UserDetails object with the user credentials information
	 * @return UserDetails -if the authentication is successful then it returns UserDetails object with more information
	 * about user.else returns null
	 */
	public UserDetails authenticateUser(UserDetails user) {
		UserDetails userDet = null;
		try {
			String query = "Select u.username,ud.firstname,ud.lastname,ud.usertype,u.userid from user u join UserDetails ud on u.userid=ud.userid where u.username='"
					+ user.getUsername()
					+ "' and u.password='"
					+ user.getPassword() + "'";
			datapointer = DataPointerFactory.getDataSource(DataSource.MySql);
			JSONArray userList = datapointer.executeQuery(query);
			if (userList != null && userList.length() > 0) {
				do {
					JSONObject jsonObj = userList.getJSONObject(0);
					String userType = jsonObj.get("UserType").toString();
					if (user.getUsertype() != null
							&& !user.getUsertype().equals(userType))
						return null;
					else if (user.getUsertype() == null
							&& userType.equals("Admin"))
						return null;
					userDet = new UserDetails();
					userDet.setUsername(jsonObj.get("Username").toString());
					userDet.setFirstname(jsonObj.get("Firstname").toString());
					userDet.setLastname(jsonObj.get("Lastname").toString());
					String typeOfUsr=jsonObj.get("UserType").toString();
					if(typeOfUsr!=null){
					if(typeOfUsr.equals("Student"))
						userDet.setUsertype(UserType.Student);
					else if(userType.equals("Employee"))
						userDet.setUsertype(UserType.Employee);
					else
						userDet.setUsertype(UserType.Admin);
					}					
					userDet.setUserId(jsonObj.getInt("UserId"));
				} while (false);
			} else {
				return null;
			}
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return userDet;
	}
	/** 
	 * Check the user name exists in database or not
	 * @param username - username to be validated
	 * @return boolean -true if username exists in database.else false
	 */
	public boolean isUsernameExists(String username) {
		try {
			datapointer = DataPointerFactory.getDataSource(DataSource.MySql);
			String query = "select count(*) as userCount from User where username='"
					+ username + "'";
			Object resp = datapointer.executeScalar(query);
			Integer userCount = Integer.valueOf(resp != null ? resp.toString()
					: "0");		
			if (userCount > 0)
				return true;
			else
				return false;

		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return false;
	}
	/** 
	 * Saves the user favorites list in the database
	 * @param userId - userId under which the favorites needs to be saved
	 * @param assetKeys - List of assetKeys which needs to be saved in database.
	 * @return boolean -true if it gets save successfully else false.
	 */
	public boolean saveUserFavourties(int userId, List<String> assetKeys) {
		boolean resp = false;
		try {

			StringBuilder query = new StringBuilder();
			datapointer = DataPointerFactory.getDataSource(DataSource.MySql);
			for (String asset : assetKeys) {
				ArrayList<Object> fieldsList = new ArrayList<Object>();
				fieldsList.add(userId);
				fieldsList.add(asset);
				String assetQuery = "insert into userFavourite(userId,AssetKey) values("
						+ formQuery(fieldsList) + ");";
				query.append(assetQuery);
				String favQuery = "";
				if (query.lastIndexOf("\n") != -1) {
					favQuery = query.toString().replace("\r", "");
				}
				favQuery = query.toString().replace("\r", "");
				resp = datapointer.executeUpdate(favQuery);
				query = new StringBuilder();
				if (!resp)
					break;
			}
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return resp;
	}
	/** 
	 * Gets the user professional(University or Employer) address
	 * @param userId - userId for whom the address is required
	 * @param userType - UserType Enumeration which specifies the user type.
	 * @return String -returns Address if the user is available else returns null.
	 */
	public String getUserAddress(int userId, UserType userType) {
		Object resp = null;
		try {
			datapointer = DataPointerFactory.getDataSource(DataSource.MySql);
			String query = null;
			if (userType==UserType.Student)
				query = "select CONCAT_WS(',',s.doorno,s.streetname,c.cityname,s.zipcode) as address from Student_user s "
						+ "join City c on s.cityid=c.cityid where userid="
						+ userId;
			else
				query = "select CONCAT_WS(',',s.doorno,s.streetname,c.cityname,s.zipcode) as address from Employee_user s "
						+ "join City c on s.cityid=c.cityid where userid="
						+ userId;		
			System.out.println(query);
			resp = datapointer.executeScalar(query);
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return resp != null ? resp.toString() : "";
	}
	/** 
	 * Saves the Asset object provided in the Database
	 * @param asset - Object of Assets Class which needs to be inserted	
	 * @return boolean - returns true if it gets saved successfully else false.
	 */
	public boolean saveNewAsset(Assets asset) {
		try {
			JSONObject assetJson = createJsonForAsset(asset);
			datapointer = DataPointerFactory.getDataSource(DataSource.NoSql);
			assetJson.put("keySpace", "assetdetails");
			datapointer.executeUpdate(assetJson.toString());
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return true;
	}
	/** 
	 * Searches for the Assets based on analysis provided by the user. It converts the userAnalysis in 
	 * the JsonFormat and uses it as query.
	 * @param userAnal - Object of UserAnalysis Class against which query need to generated.
	 * @return List<Assets> - The assets list produced by the querying the database against userAnalysis object
	 */
	public List<Assets> searchAssets(UserAnalysis userAnal) {
		JSONArray jsonArrayList = null;
		List<Assets> assetList = new ArrayList<Assets>();
		try {
			formQuery("city", Operators.equals, userAnal.getLocation());
			formQuery("noofbedrooms", Operators.greaterThanEqual,
					userAnal.getPreferrableBrCount());			
			formQuery("hasBasement", Operators.equals,userAnal.getHasBasement());
			formQuery("hasCentralizedHeating",Operators.equals,userAnal.isHasCentralizedHeating() ? "YES" : "NO");
			formQuery("isDishWasherAvailable", Operators.equals,
					userAnal.isDishWasherAvailable() ? "YES" : "NO");
			formQuery("numberOfSprinkler", Operators.greaterThan,
					userAnal.getNumberOfSprinkler());
			formQuery("noOfParkinglots", Operators.greaterThan,
					userAnal.getNoOfParkinglots());
			formQuery("isSwimmingPoolAvailable", Operators.equals,
					userAnal.isSwimmingPoolAvailable() ? "YES" : "NO");
			formQuery("isCentralizedCoolingAvailable", Operators.equals,
					userAnal.isCentralizedCoolingAvailable() ? "YES" : "NO");
			formQuery("hasClubMembership", Operators.equals,
					userAnal.getHasClubMembership());
			formQuery("hasGarage", Operators.equals, userAnal.getHasGarage());
			formQuery("hasGym", Operators.equals, userAnal.getHasGym());
			formQuery("isFamilyRoomAvailable", Operators.equals,
					userAnal.isFamilyRoomAvailable() ? "YES" : "NO");
			formQuery("isNewHome", Operators.equals,
					userAnal.isNewHome() ? "YES" : "NO");
			formQuery("minPriceRange", Operators.greaterThan,
					userAnal.getMinPriceRsange());
			formQuery("maxPriceRance", Operators.lessthan,
					userAnal.getMaxPriceRance());
			formQuery("typeFloor", Operators.equals,userAnal.getTypeFloor());
			formQuery("isMastersBedRoomAvailable", Operators.equals,
					userAnal.isMastersBedRoomAvailable() ? "YES" : "NO");
			formQuery("isFirePlaceAvailable", Operators.equals,
					userAnal.isFirePlaceAvailable() ? "YES" : "NO");
			formQuery("isbalconyAvailable", Operators.equals,
					userAnal.isIsbalconyAvailable() ? "YES" : "NO");
			formQuery("isDownstairsRoomAvailable", Operators.equals,
					userAnal.isDownstairsRoomAvailable() ? "YES" : "NO");
			formQuery("isGuestRoomAvailable", Operators.equals, userAnal.isGuestRoomAvailable() ? "YES" : "NO");
			formQuery("isLaundaryAvailable", Operators.equals, userAnal.isLaundaryAvailable() ? "YES" : "NO");
			formQuery("isOfficeRoomAvailable", Operators.equals, userAnal.isOfficeRoomAvailable() ? "YES" : "NO");
			formQuery("isRecreationRoomAvailable", Operators.equals, userAnal.isRecreationRoomAvailable() ? "YES" : "NO");
			formQuery("isSportsRoomAvailable", Operators.equals, userAnal.isSportsRoomAvailable() ? "YES" : "NO");
			setKeySpace("assetdetails");
			String jsonString = query.toString();
			System.out.println("jsonString :" + jsonString);
			datapointer = DataPointerFactory.getDataSource(DataSource.NoSql);
			jsonArrayList = datapointer.executeQuery(jsonString);
			assetList = retrieveAssetsFromJson(jsonArrayList);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return assetList;
	}
	/** 
	 * Searches for the Asset in the database for the provided assetkey.
	 * @param assetKey - assetKey which needs to be searched
	 * @return Assets - returns the object of the Assets class if it finds for the assetKey,else null
	 */
	public Assets getAssetBasedonKey(String assetKey) {
		JSONArray jsonArrayList = null;
		List<Assets> assetList = new ArrayList<Assets>();
		try {
			formQuery("assetid", Operators.equals, assetKey);
			setKeySpace("assetdetails");
			String jsonString = query.toString();
			datapointer = DataPointerFactory.getDataSource(DataSource.NoSql);
			jsonArrayList = datapointer.executeQuery(jsonString);
			assetList = retrieveAssetsFromJson(jsonArrayList);
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		if (assetList != null && assetList.size() > 0) {			
			return assetList.get(0);
		}
		return null;
	}
	/** 
	 * Get the Favorites List which are saved by the user. This method extracts the assetsKeyids for the user and 
	 * looks up in Mongo to get more information about each Asset.
	 * @param userId - userId value for which favorites needs to extracted
	 * @return List<Assets> - The assets list produced by the querying the database against userID value
	 */
	public List<Assets> getUserFavourites(int userId) {
		List<Assets> assetsList = null;
		try {
			String[] assetIds = getUserFavAssetIds(userId);
			formQuery("assetid", Operators.in, assetIds);
			setKeySpace("assetdetails");
			String jsonString = query.toString();			
			datapointer = DataPointerFactory.getDataSource(DataSource.NoSql);
			JSONArray jsonArrayList = datapointer.executeQuery(jsonString);
			assetsList = retrieveAssetsFromJson(jsonArrayList);
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return assetsList;
	}	
	/** 
	 * Gets all Assets in the form of Hashtable by using assetkey as Key of the hashtable
	 * @return Hashtable - returns the instance of HashTable  with Asset fields as keys and and asset information as values.
	 */
	public Hashtable<String, String> getAllAssets() {
		JSONObject assetJson = null;
		JSONArray jsonArrayList = null;
		Hashtable<String, String> assetList = new Hashtable<String, String>();
		try {
			setKeySpace("assetdetails");
			String jsonString = query.toString();
			datapointer = DataPointerFactory.getDataSource(DataSource.NoSql);
			jsonArrayList = datapointer.executeQuery(jsonString);
			for (int assetCnt = 0; assetCnt < jsonArrayList.length(); assetCnt++) {
				assetJson = jsonArrayList.getJSONObject(assetCnt);
				assetList.put(assetJson.getString("assetid"),
						assetJson.toString());
			}
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return assetList;
	}
	/** 
	 * Get the Cities List.
	 * @return List<City> - Return the list of City Class objects with the city information
	 */
	public List<City> getCities() {
		City city = null;
		List<City> cityList = null;
		try {
			datapointer = DataPointerFactory.getDataSource(DataSource.MySql);
			String query = "select CityId,CityName from City";
			JSONArray cityJsonArray = datapointer.executeQuery(query);
			cityList = new ArrayList<City>();
			for (int cityCnt = 0; cityCnt < cityJsonArray.length(); cityCnt++) {
				JSONObject cityObj = cityJsonArray.getJSONObject(cityCnt);
				city = new City();
				city.setCityId(cityObj.getInt("CityId"));
				city.setCityname(cityObj.getString("CityName"));
				cityList.add(city);
			}
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return cityList;
	}
	
	/** 
	 * Registers the user in OnlineRealtor Database.
	 * @param usersignup - instance of an UserDetails with the information to needs to register
	 * @return boolean - returns true if user signs up successfully,else false
	 */
	public boolean signUpUser(UserDetails usersignup) {
		ArrayList<Object> fieldsList = new ArrayList<Object>();
		fieldsList.add(usersignup.getUsername());
		fieldsList.add(usersignup.getPassword());
		fieldsList.add(true);
		int userId = 0;
		ArrayList<Object> fieldsud = new ArrayList<Object>();
		// fieldsud.add(getGender());

		try {
			datapointer = DataPointerFactory.getDataSource(DataSource.MySql);
			userId = datapointer.executeUpdate(
					"insert into user(username,password,isactive) values("
							+ formQuery(fieldsList) + ")",
					java.sql.Statement.RETURN_GENERATED_KEYS);			
			fieldsud.add(userId);
			fieldsud.add(usersignup.getFirstname());
			fieldsud.add(usersignup.getLastname());
			fieldsud.add(usersignup.getPhonenum());
			fieldsud.add(usersignup.getGender());
			fieldsud.add(usersignup.getUsertype());
			datapointer
					.executeUpdate(
							"insert into userdetails(userID,firstname,lastname,phoneno,gender,usertype) values("
									+ formQuery(fieldsud) + ")",
							java.sql.Statement.RETURN_GENERATED_KEYS);
			// TODO Change Below
			// Change here to the instance of Student/Employee/BusinessUserPref
			if (usersignup instanceof StudentUserDetails) {
				StudentUserDetails studentUser = (StudentUserDetails) usersignup;
				ArrayList<Object> fields = new ArrayList<Object>();
				fields.add(userId);
				fields.add(studentUser.getUnivName());
				fields.add(studentUser.getZipcode());
				fields.add(studentUser.getCityId());
				fields.add(studentUser.getStreet());
				fields.add(studentUser.getDoorNo());
				datapointer
						.executeUpdate(
								"insert into Student_user(userID,univname,zipcode,cityId,Streetname,doorno) values("
										+ formQuery(fields) + ")",
								java.sql.Statement.RETURN_GENERATED_KEYS);
			}
			// Change here to the instance of Student/Employee/BusinessUserPref
			else if (usersignup instanceof EmployeeUserDetails) {
				ArrayList<Object> fields = new ArrayList<Object>();
				EmployeeUserDetails employeeUser = (EmployeeUserDetails) usersignup;
				fields.add(userId);
				fields.add(employeeUser.getEmployer());
				fields.add(employeeUser.getZipcode());
				fields.add(employeeUser.getCityId());
				fields.add(employeeUser.getStreet());
				fields.add(employeeUser.getDoorNo());
				datapointer
						.executeUpdate(
								"insert into Employee_user(userID,employername,zipcode,cityId,Streetname,doorno) values("
										+ formQuery(fields) + ")",
								java.sql.Statement.RETURN_GENERATED_KEYS);
			}

		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return true;
	}
	
	/** 
	 * Converts the object of the asset class to JSON format.
	 * @param asset - Instance of a Asset class with the asset information
	 * @return JSONObject - returns the instance of JSONObject with the information provided in asset instance.
	 */
	private JSONObject createJsonForAsset(Assets asset) {
		JSONObject assetJson = null;
		try {
			Hashtable<String, String> fieldMap = getFieldKeys();
			assetJson = new JSONObject();
			// Get the Field Info
			Field[] assetFields = asset.getClass().getDeclaredFields();
			System.out.println("Firled Count :" + assetFields.length);
			for (Field field : assetFields) {
				if (field.getName() != null) {
					field.setAccessible(true);
					assetJson.put(
							fieldMap.get(field.getName()) == null ? field
									.getName() : fieldMap.get(field.getName()),
							field.get(asset));
				}
			}

		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return assetJson;
	}
	/** 
	 * Gets the Fields keys which are mapping to the Asset Entity by looking into FieldMapping.xml	 
	 * @return Hashtable - returns the instance of HashTable  with Asset fields as keys and Mapping column names as values.
	 */
	private Hashtable<String, String> getFieldKeys() {
		Hashtable<String, String> fieldMap = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse("C:\\FieldMapping.xml");
			fieldMap = new Hashtable<String, String>();
			NodeList mappingList = dom.getElementsByTagName("Field_Mapping");
			for (int mapCount = 0; mapCount < mappingList.getLength(); mapCount++) {
				Node firstPersonNode = mappingList.item(mapCount);
				if (firstPersonNode.getNodeType() == Node.ELEMENT_NODE) {
					Element firstPersonElement = (Element) firstPersonNode;
					NodeList firstNameList = firstPersonElement
							.getElementsByTagName("property");
					for (int fieldCount = 0; fieldCount < firstNameList
							.getLength(); fieldCount++) {
						Node n = firstNameList.item(fieldCount);						
						NamedNodeMap nm = n.getAttributes();
						fieldMap.put(nm.getNamedItem("name").getNodeValue(), nm
								.getNamedItem("columnKey").getNodeValue());
					}
				}
			}
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return fieldMap;
	}
	/** 
	 * Converts the the JSONArray object into Collection of Assets.
	 * @param assetsArray - instance of JSONArray with the information about the each Asset
	 * @return List<Assets> - returns the Collection of Asset class instances with the information provided in JSONArray input.
	 */
	private List<Assets> retrieveAssetsFromJson(JSONArray assetsArray) {
		List<Assets> assetList = new ArrayList<Assets>();
		// Retrieve Details from
		try {
			for (int i = 0; i < assetsArray.length(); i++) {
				JSONObject jsonObj = assetsArray.getJSONObject(i);
				Assets asset = new Assets();
				asset.setAssetKey(jsonObj.getString("assetid")
						.replace("\n", ""));
				asset.setAddressline1(jsonObj.get("addressline1").toString());
				asset.setAddressline2(jsonObj.get("addressline2").toString());
				asset.setTypesofrooms(jsonObj.get("typesofrooms").toString());
				// asset.setAmenities(jsonObj.get("amenities").toString());
				asset.setFloortype(jsonObj.get("typeFloor").toString());
				asset.setTypeofroof(jsonObj.get("typeofroof").toString());
				asset.setFullyfurnished(jsonObj.get("fullyfurnished")
						.toString());
				asset.setClubmembership(jsonObj.get("hasClubMembership")
						.toString());
				asset.setEstgasbill(Double.valueOf(jsonObj.get("Estgasbill")
						.toString()));
				asset.setDistfromdowntown(Integer.valueOf(jsonObj.get(
						"Dist from downtown").toString()));
				asset.setOwnercontact(jsonObj.get("ownercontact").toString());
				asset.setAgeofhome(Integer.valueOf(jsonObj.get("ageofhome")
						.toString()));
				asset.setSection(Integer.valueOf(jsonObj.get("section")
						.toString()));
				asset.setCity(jsonObj.get("city").toString());
				asset.setOwnername(jsonObj.get("ownername").toString());
				asset.setPrice(Double.valueOf(jsonObj.get("minPriceRange")
						.toString()));
				asset.setLatitude(Double.valueOf(jsonObj.get("latitude")
						.toString()));
				asset.setLongitude(Double.valueOf(jsonObj.get("longitude")
						.toString()));
				asset.setNoofbedrooms(Integer.valueOf(jsonObj.get(
						"noofbedrooms").toString()));
				assetList.add(asset);
			}
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return assetList;
	}
	
	
	
	/** 
	 * Forms the collections of fields as comma separated keys
	 * @param fields - instance of an ArrayList object with the fields
	 * @return String - returns the comma separated fields
	 */
	private String formQuery(ArrayList<Object> fields) {

		StringBuilder formatted = new StringBuilder("");
		String formedQuery = "";
		try {
			for (Object obj : fields) {
				if ((obj instanceof String) || (obj instanceof Date))
					formatted.append("'" + obj.toString() + "',");
				if ((obj instanceof Integer) || (obj instanceof Float)
						|| (obj instanceof Double) || (obj instanceof Boolean))
					formatted.append("" + obj.toString() + ",");
			}
			if (!(formatted.toString().equals("")))
				formedQuery = formatted.substring(0, formatted.length() - 1);
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return formedQuery.toString();
	}
	/** 
	 * Forms the JSON Object based on the arguments passed which follows the JSON Structure
	 * @param operand1 - String operand of the condition
	 * @param operand2 - String operand of the condition
	 * @param Operator - Operator Enum which is getting used between operands 
	 */
	private void formQuery(String operand1, Operators operator, Object operand2) {
		try {
			if (query == null) {
				query = new JSONObject();
				JSONArray conditionsArray = new JSONArray();
				query.put("conditions", conditionsArray);
			}
			// Get the exisiting Conditions
			if (operand2 != null) {
				JSONArray conditions = (JSONArray) query.get("conditions");
				JSONObject currentCondition = new JSONObject();
				currentCondition.put("operand1", operand1);
				currentCondition.put("operator", operator.getText());
				currentCondition.put("operand2", operand2);
				conditions.put(currentCondition);
			}
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	/** 
	 * Sets the KeySpace in JSONCOllection.
	 * @param keySpace - collection name	 
	 */
	private void setKeySpace(String keySpace) {
		try {
			if (query == null)
				query = new JSONObject();
			query.put("keySpace", keySpace);
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	/** 
	 * Get the Favorites List which are saved by the user. This method extracts the assetsKeyids for the user.
	 * @param userId - userId value for which favorites needs to extracted
	 * @return String[] - The assetIds array produced by the querying the database against userID value
	 */
	private String[] getUserFavAssetIds(int userId) {
		IDataPointer dataPointer = DataPointerFactory
				.getDataSource(DataSource.MySql);
		String[] assetIds = null;
		try {
			String assetQuery = "select distinct AssetKey from UserFavourite where userid="
					+ userId;
			JSONArray jsonAssets = dataPointer.executeQuery(assetQuery);
			assetIds = new String[jsonAssets.length()];
			for (int i = 0; i < jsonAssets.length(); i++) {
				JSONObject jsonObj = jsonAssets.getJSONObject(i);
				assetIds[i] = jsonObj.getString("AssetKey");
			}

		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return assetIds;
	}

}
