package uic.onlinerealtor.dataaccess;


import org.json.JSONArray;
import org.json.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import uic.onlinerealtor.dataaccess.DataPointerFactory.Operators;



/**
 * This class implements the IDataPointer Interface. It provides the implemented methods which uses Mongo API 
 * to interact with the Mongo Database. 
 * Any results produced by the executing the query from this object will be converted into JSON format and returns 
 * as JSONArray object.
 * @author OnlineRealtor
 * @version 1.0
 */
public class RealtorMongoDataSource implements IDataPointer {
	String database = null;
	String mongoServer = null;

	/** 
	 * Constructs an empty RealtorMongoDataSource by setting the Mongo DB Server location	 *
	 */
	public RealtorMongoDataSource()  {
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("C:\\config.properties"));
			mongoServer = prop.getProperty("mongoDBServer");
			database = prop.getProperty("mondoDB");
		} catch (Exception ex) {
			//log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	/** 
	 * Execute the given query and returns the result in the form of JSONArray
	 * @overrides executeQuery in IDataPointer Interface
	 * @param query - query which represents the required data in JSON format
	 * @return JSONArray object which contains data produced by the query
	 */
	public JSONArray executeQuery(String query) {
		DBCursor cursor = null;
		JSONArray jsonArray = new JSONArray();
		try {
			JSONObject json = new JSONObject(query);
			Mongo m = new Mongo();
			DB db = m.getDB(database);
			String collectionName = (String) json.get("keySpace");
			DBCollection coll = db.getCollection(collectionName);
			BasicDBObject basicDbObj = new BasicDBObject();
			if (json.has("conditions")) {
				JSONArray conditionsArr = (JSONArray) json.get("conditions");
				for (int conditionCount = 0; conditionCount < conditionsArr
						.length(); conditionCount++) {
					JSONObject conditionToApply = conditionsArr
							.getJSONObject(conditionCount);
					String operand1 = conditionToApply.getString("operand1");
					Object operand2 = conditionToApply.get("operand2");
					String operator = conditionToApply.getString("operator");
					if (operator.equals(Operators.equals.getText()))
						basicDbObj.put(operand1, operand2);
					else {
						if (operand2 instanceof Integer)
							basicDbObj.put(
									operand1,
									new BasicDBObject(operator, Integer
											.valueOf(operand2.toString())));
						else if (operand2 instanceof String)
							basicDbObj.put(operand1, new BasicDBObject(
									operator, operand2.toString()));
						else if (operand2 instanceof Double)
							basicDbObj.put(
									operand1,
									new BasicDBObject(operator, Double
											.valueOf(operand2.toString())));
						else if (operand2 instanceof Float)
							basicDbObj.put(
									operand1,
									new BasicDBObject(operator, Float
											.valueOf(operand2.toString())));
						else if (operand2 instanceof JSONArray) {							
							JSONArray jsonArr = (JSONArray) operand2;
							String[] operandValues = new String[jsonArr
									.length()];
							for (int jsonCount = 0; jsonCount < jsonArr
									.length(); jsonCount++)
								operandValues[jsonCount] = String
										.valueOf(jsonArr.get(jsonCount));
							basicDbObj.put(operand1, new BasicDBObject(
									operator, operandValues));
						}
					}
				}
			}			
			cursor = coll.find(basicDbObj);
			Set<String> columnsList = null;
			boolean gotColumnsList = false;
			while (cursor.hasNext()) {
				DBObject object = cursor.next();
				if (!gotColumnsList) {
					columnsList = object.keySet();
					gotColumnsList = true;
				}
				JSONObject jsonResult = new JSONObject();
				for (String column : columnsList) {
					jsonResult.put(column, object.get(column)!=null?object.get(column).toString():"");
				}
				jsonArray.put(jsonResult);
			}
		} catch (Exception ex) {
			//log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			cursor.close();
		}
		return jsonArray;
	}
	/** 
	 * Execute the given query and returns the Single result from the Results
	 * @overrides executeScalar in IDataPointer Interface
	 * @param query - query which represents the required data in JSON format
	 * @return Object - returns the result in the form of Object
	 */
	public Object executeScalar(String query){
		//Empty Declaration
		return null;
	}
	/** 
	 * Execute the given query and updates the data in NoSqlDatabase
	 * @overrides executeUpdate in IDataPointer Interface
	 * @param query - query which represents the required data in JSON format
	 * @return true - if it gets updated returns true else false
	 */
	public boolean executeUpdate(String query) {
		try {
			JSONObject json = new JSONObject(query);
			Mongo m = new Mongo();
			DB db = m.getDB(database);
			String collectionName = (String) json.get("keySpace");
			DBCollection coll = db.getCollection(collectionName);
			BasicDBObject basicDbObj = new BasicDBObject();
			Iterator keys = json.keys();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				basicDbObj.append(key, json.get(key));
			}
			coll.insert(basicDbObj);
		} catch (Exception ex) {
			//log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return true;
	}

	/**
	 * Execute the given query and updates the data in NoSqlDatabase
	 *  @overrides executeUpdate in IDataPointer Interface
	 * @param query - query which represents the required data in JSON format
	 * @param generatedKeys - The Keys which need to be returned
	 * @return int - returns the number of rows affected
	 */
	public int executeUpdate(String query, int generatedKeys) {
		//Empty Declaration
		return 1;
	}
}
