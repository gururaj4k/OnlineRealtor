package uic.onlinerealtor.dataaccess;

import org.json.JSONArray;

/**
 * This interface defines the general methods for querying and updating any database. Any Class implemented by this interface 
 * should implement the methods based on the specfic database API.
 * Any results produced by the executing the query from this object will be converted into JSON format and returns 
 * as JSONArray object.
 * @author OnlineRealtor
 * @version 1.0
 */
public interface IDataPointer {
	/** 
	 * Execute the given query and returns the result in the form of JSONArray	 * 
	 * @param query - query which represents the required data in JSON format
	 * @return JSONArray object which contains data produced by the query
	 */
	JSONArray executeQuery(String query);
	/** 
	 * Execute the given query and updates the data in NoSqlDatabase
	 * @param query - query which represents the required data in JSON format
	 * @return true - if it gets updated returns true else false
	 */
	boolean executeUpdate(String query);
	
	/**
	 * Execute the given query and updates the data in NoSqlDatabase
	 * @param query - query which represents the required data in JSON format
	 * @param generatedKeys - The Keys which need to be returned
	 * @return int - returns the number of rows affected
	 */
	int executeUpdate(String query,int generatedKeys);
	/** 
	 * Execute the given query and returns the Single result from the Result
	 * @param query - query which represents the required data in JSON format
	 * @return Object - returns the result in the form of Object
	 */
	Object executeScalar(String query); 
}
