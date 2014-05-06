package uic.onlinerealtor.dataaccess;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class implements the IDataPointer Interface. It provides the implemented
 * methods which uses MySql Database API to interact with the MySql Database.
 * Any results produced by the executing the query from this object will be
 * converted into JSON format and returns as JSONArray object. *
 * 
 * @author OnlineRealtor
 * @version 1.0
 */
public class RealtorRelationalDataSource implements IDataPointer {
	Connection conn = null;
	String database = null;
	String user = null;
	String pwd = null;
	Statement statement = null;

	/**
	 * Constructs an empty RealtorRelationalDataSource by setting the MySql
	 * database credentials
	 */
	public RealtorRelationalDataSource() {
		try {

			Properties prop = new Properties();
			prop.load(new FileInputStream("C:\\config.properties"));
			database = prop.getProperty("mySqldatabase");
			user = prop.getProperty("mySqluser");
			pwd = prop.getProperty("mySqlpassword");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(database, user, pwd);
			statement = conn.createStatement();
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Execute the given query and returns the result in the form of JSONArray	 * 
	 * @overrides executeQuery in IDataPointer Interface
	 * @param query which represents the required data in JSON format
	 * @return JSONArray object which contains data produced by the query
	 */
	public JSONArray executeQuery(String query) {
		JSONArray jsonArray = null;
		try {
			ResultSet resultSet = statement.executeQuery(query);
			jsonArray = CreateDCJson(resultSet);
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception ex) {
				// log the exception
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
		}
		return jsonArray;
	}

	/**
	 * Execute the given query and updates the data in NoSqlDatabase
	 * @overrides executeUpdate in IDataPointer Interface
	 * @param query which represents the required data in JSON format
	 * @return true - if it gets updated returns true else false
	 */
	public boolean executeUpdate(String query) {
		int resp = 0;
		try {
			resp = statement.executeUpdate(query);
			if (resp > 0)
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
	 * @overrides executeUpdate in IDataPointer Interface Execute the given query and updates the data in NoSqlDatabase
	 * @param query which represents the required data in JSON format
	 * @param generatedKeys The Keys which need to be returned
	 * @return int - returns the number of rows affected
	 */
	public int executeUpdate(String query, int generatedKeys) {
		// boolean check=false;
		int resp = 0;
		try {
			statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.getGeneratedKeys();
			while (rs.next()) {
				resp = rs.getInt(1);
			}
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return resp;
	}

	/**
	 * Execute the given query and returns the Single result from the Results
	 * @overrides executeScalar in IDataPointer Interface
	 * @param query which represents the required data in JSON format
	 * @return Object - returns the result in the form of Object
	 */
	public Object executeScalar(String query) {

		try {

			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				System.out.println("I printted the " + resultSet.getObject(1));
				return resultSet.getObject(1);
			}
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Convert the ResultSet object into JSONArray object
	 * @param Java.sql.ResultSet object with the data populated.
	 * @return JSONArray - returns the JSONArray object with the data produced
	 * by Enumerating through the ResultSet.
	 */
	private JSONArray CreateDCJson(ResultSet rs) {
		JSONArray jsonArray = new JSONArray();
		try {
			ResultSetMetaData rsMetaData = rs.getMetaData();

			while (rs.next()) {
				int numColumns = rsMetaData.getColumnCount();
				JSONObject obj = new JSONObject();
				for (int i = 1; i < numColumns + 1; i++) {
					String column = rsMetaData.getColumnName(i);
					if (rsMetaData.getColumnType(i) == java.sql.Types.BIGINT) {
						obj.put(column, rs.getInt(column));
					} else if (rsMetaData.getColumnType(i) == java.sql.Types.BOOLEAN) {
						obj.put(column, rs.getBoolean(column));
					} else if (rsMetaData.getColumnType(i) == java.sql.Types.DOUBLE) {
						obj.put(column, rs.getDouble(column));
					} else if (rsMetaData.getColumnType(i) == java.sql.Types.FLOAT) {
						obj.put(column, rs.getFloat(column));
					} else if (rsMetaData.getColumnType(i) == java.sql.Types.INTEGER) {
						obj.put(column, rs.getInt(column));
					} else if (rsMetaData.getColumnType(i) == java.sql.Types.NVARCHAR) {
						obj.put(column, rs.getNString(column));
					} else if (rsMetaData.getColumnType(i) == java.sql.Types.VARCHAR) {
						obj.put(column, rs.getString(column));
					} else if (rsMetaData.getColumnType(i) == java.sql.Types.DATE) {
						obj.put(column, rs.getDate(column));
					} else if (rsMetaData.getColumnType(i) == java.sql.Types.TIMESTAMP) {
						obj.put(column, rs.getTimestamp(column));
					} else {
						obj.put(column, rs.getObject(column));
					}
				}
				jsonArray.put(obj);
			}
		} catch (Exception ex) {
			// log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return jsonArray;
	}
}
