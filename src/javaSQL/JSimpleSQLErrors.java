package javaSQL;

public class JSimpleSQLErrors {
	
	
	protected static void connectionError() {
		System.out.println("There was an error connecting to the Database. Check your hostname, database name, username, or password.");
	}
	protected static void createDatabaseError() {
		System.out.println("There was an issue creating your Database. Make sure the database name isn't already used. Also, check your connection information.");
	}
	protected static void useDatabaseError() {
		System.out.println("There was an issue selecting your Database. Make sure the database exists. Also, check your connection information.");
	}
	protected static void dropDatabaseError() {
		System.out.println("There was an issue dropping your Database. Make sure the database exists. Also, check your connection information.");
	}
	protected static void createTableError() {
		System.out.println("There was an issue creating your Table. Make sure the table name isn't already used. Also, check your connection information.");
	}
	protected static void deleteTableError() {
		System.out.println("There was an issue dropping your Table. Make sure the table exists. Also, check your connection information.");
	}
	protected static void defaultColumnNameError() {
		System.out.println("This column name is Reserved Word specific for this API. Please choose another name.");
	}

}
