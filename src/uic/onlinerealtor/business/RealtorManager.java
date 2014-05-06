package uic.onlinerealtor.business;

import java.util.Hashtable;
import java.util.List;
import uic.onlinerealtor.entities.*;
import uic.onlinerealtor.entities.UserPreference.UserType;
import uic.onlinerealtor.dataaccess.*;
import uic.onlinerealtor.drools.IDroolsEngine;
import uic.onlinerealtor.drools.OnlineRealtorDroolsEngine;

/**
 * The object used to perform any kind of business logic of the OnlineRealtor.
 * This is used to interact with DataAccess layer and Drools Rules Engine An
 * Instance of RealtorManager contains RealtorDAO reference which is used for
 * any kind of database operations
 * 
 * @author OnlineRealtor Team
 * @version 1.0
 */
public class RealtorManager {
	private RealtorDAO realtorDao = null;

	/**
	 * Registers the user in OnlineRealtor Database. Before registering a new
	 * user this method checks the username availability. In case username is
	 * not available it fails the registration process.	 * 
	 * @param usersignup - instance of an UserDetails with the information to needs to
	 * register
	 * @return boolean - returns true if user signs up successfully,else false
	 */
	public boolean signupUser(UserDetails usersignup) {
		try {
			realtorDao = new RealtorDAO();
			if (!validateUser(usersignup)) {
				return realtorDao.signUpUser(usersignup);
			} else {
				return false;
			}
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return false;
	}
	/** 
	 * Authenticate the user credentials by connecting to DataAccessLayer 
	 * @param user - UserDetails object with the user credentials information
	 * @return UserDetails -if the authentication is successful then it returns UserDetails object with more information
	 * about user.else returns null
	 */
	public UserDetails authenticateUser(UserDetails user) {
		try {
			realtorDao = new RealtorDAO();
			return realtorDao.authenticateUser(user);
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Save the asset to the AssetList
	 * @param asset- Object of Asset Class which needs to be inserted.
	 * @return true - if it gets saves successfully else false
	 */
	public boolean saveNewAsset(Assets asset) {
		try {
			realtorDao = new RealtorDAO();
			return realtorDao.saveNewAsset(asset);
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return false;
	}
	/** 
	 * Searches for the Assets based on user preference. This methods executes the rules on the userpreference object
	 * and get the useranalysis done by Drools engine. and uses the result of drools as input to query the database.
	 * @param userPref - Object of UserPreference Class with the user preferences for searching property.
	 * @return List<Assets> - The assets list produced by the querying the database against userAnalysis object
	 */
	public List<Assets> searchAssets(UserPreference userPref) {
		try {
			realtorDao = new RealtorDAO();		
			UserAnalysis userAnalysis = getUserAnalysis(userPref);			
			userAnalysis.setLocation(userPref.getPreferredLocation());
			List<Assets> assetList = realtorDao.searchAssets(userAnalysis);
			for (Assets asset : assetList) {
				asset.setPrice(asset.getPrice()
						* userAnalysis.getBaseMultiplicationFactor());
			}
			return assetList;
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	/** 
	 * Gets the user professional(University or Employer) address by connecting to RealtorDAO
	 * @param userId - userId for whom the address is required
	 * @param userType - UserType Enumeration which specifies the user type.
	 * @return String -returns address of the user
	 */
	public String getUserAddress(int userId, UserType userType) {
		try {
			realtorDao = new RealtorDAO();
			String address = realtorDao.getUserAddress(userId, userType);		
			System.out.println("Address :"+address);
			return address;
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	/** 
	 * Get the Favorites List which are saved by the user by connecting to RealtorDAO
	 * @param userId - userId value for which favorites needs to extracted
	 * @return List<Assets> - The assets list produced by the querying the database against userID value
	 */
	public List<Assets> getUserFavourites(int userId) {
		try {
			realtorDao = new RealtorDAO();
			return realtorDao.getUserFavourites(userId);
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	/** 
	 * Gets all Assets in the form of Hashtable by using assetkey as Key of the hashtable
	 * @return Hashtable - returns the instance of HashTable  with Asset fields as keys and and asset information as values.
	 */
	public Hashtable<String, String> getAllAssets() {
		try {
			realtorDao = new RealtorDAO();
			return realtorDao.getAllAssets();
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	/** 
	 * Searches for the Asset in the database for the provided assetkey.
	 * @param assetKey - assetKey which needs to be searched
	 * @return Assets - returns the object of the Assets class if it finds for the assetKey,else null
	 */
	public Assets getAssetBasedonKey(String assetKey) {
		try {
			realtorDao = new RealtorDAO();
			return realtorDao.getAssetBasedonKey(assetKey);
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	/** 
	 * Get the Cities List.
	 * @return List<City> - Return the list of City Class objects with the city information
	 */
	public List<City> getCities() {
		try {
			realtorDao = new RealtorDAO();
			return realtorDao.getCities();
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	/** 
	 * Saves the user favorites list in the database
	 * @param userId - userId under which the favorites needs to be saved
	 * @param assetKeys - List of assetKeys which needs to be saved in database.
	 * @return boolean -true if it gets save successfully else false.
	 */
	public boolean saveUserFavourties(int userId, List<String> assetKeys) {
		try {
			realtorDao = new RealtorDAO();
			return realtorDao.saveUserFavourties(userId, assetKeys);
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return false;
	}
	/** 
	 * Connects to the Drools engine through an IDroolsEngine interface and provides userpreference object as input and 
	 * get useranalysis object as output which contains processed data as per the rules defined.
	 * @param userPreference - UserPreference object with user information provided
	 * @return UserAnalysis -UserAnalysis object with the data processed by drools engine.
	 */
	private UserAnalysis getUserAnalysis(UserPreference userPreference) {
		try {
			IDroolsEngine droolsEng;
			droolsEng = new OnlineRealtorDroolsEngine();
			uic.onlinerealtor.entities.UserAnalysis userAnalysis = droolsEng
					.getUserAnalysis(userPreference);
			return userAnalysis;
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	/** 
	 * Connects to the Drools engine through an IDroolsEngine interface and provides user answered question object as input and 
	 * get question class object as output which contains next question text 
	 * @param userPreference - Question Class object with the user answer.
	 * @return UserAnalysis -Question Class object with the next question text 
	 */
	public Question getNextQuestion(Question ques) {
		try {
			IDroolsEngine droolsEng;
			droolsEng = new OnlineRealtorDroolsEngine();
			uic.onlinerealtor.entities.Question question = droolsEng
					.getNextQuestion(ques);
			return question;
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	/** 
	 * Check the user name exists in RealtorDAO
	 * @param UserDetails - username to be validated
	 * @return boolean -true if username is valid.else false
	 */
	private boolean validateUser(UserDetails userDet) {
		try {
			realtorDao = new RealtorDAO();
			boolean isUserExists = realtorDao.isUsernameExists(userDet
					.getUsername());			
			return isUserExists;
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return false;
	}
}
