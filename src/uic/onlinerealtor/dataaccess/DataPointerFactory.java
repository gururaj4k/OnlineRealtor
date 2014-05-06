package uic.onlinerealtor.dataaccess;


/**
 * The DataPointerFactory class provides the static methods to get an instance of specific data source based on the DataSource
 * enum. The RealtorDAO will interact with this factory to get the DataSource object in the form of IDataPointer. This 
 * Factory will decouple the Data source objects with Application(RealtorDAO).  
 * @author OnlineRealtor
 * @version 1.0
 */
public class DataPointerFactory {
	private static IDataPointer  datapointer;	
	public static IDataPointer getDataSource(DataSource dataSourceType){
		if(dataSourceType==DataSource.MySql)
			datapointer=new RealtorRelationalDataSource();
		else if(dataSourceType==DataSource.NoSql)
			datapointer=new RealtorMongoDataSource();
		return datapointer;
	}
	/**
	 * This Enum contains the data bases to which the Application interacts
	 * @author OnlineRealtor
	 * @version 1.0
	 */
	public enum DataSource{
		MySql,
		NoSql
	}
	/**
	 * This Enum contains distinct operators which will be used among to to perform query operations to the Database.
	 * @author OnlineRealtor
	 * @version 1.0
	 */
	public enum Operators {
		  equals("="),
		  greaterThan("$gt"),
		  lessthan("$lt"),
		  in("$in"),
		  greaterThanEqual("$gte"),
		  lessThanEqual("$lte");
		  

		  private String text;

		  /**
		 * @param text - text to be set for the enum
		 */
		Operators(String text) {
		    this.text = text;
		  }

		  /**
		 * @return respective text for the enum
		 */
		public String getText() {
		    return this.text;
		  }

		}

}

